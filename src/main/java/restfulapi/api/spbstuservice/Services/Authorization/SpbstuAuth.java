package restfulapi.api.spbstuservice.Services.Authorization;


import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class SpbstuAuth {

    public class Result {
        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        String cookie;
        String id;
        String name;
        String secondName;
        String group;
        String avatar;

        public Result(String id, String name, String secondName, String group, String avatar) {
            this.id = id;
            this.name = name;
            this.secondName = secondName;
            this.group = group;
            this.avatar = avatar;
        }
    }

    private String login;
    private String pass;

    public Result auth(String login, String pass) throws Exception {
        this.login = login;
        this.pass = pass;
        var cookie = step1();
        return fetchUser(cookie);
    }

    public Result fetchUser(String cookie) throws Exception {
        var body = fetchUserBody(cookie);
        var result = parseUser(body);
        result.setCookie(cookie);
        return result;
    }

    private Result parseUser(String body) throws JSONException {
        var jsonString = new Regexp("<script id=\"page-context\" type=\"text\\/page-context\">(.*?)</script>").matches(body).get(0);

        var json = new JSONObject(jsonString).getJSONObject("user").getJSONObject("wsAsu");
        return new Result(
                json.getString("user_id"),
                json.getString("first_name"),
                json.getString("last_name"),
                json.getJSONArray("structure").getJSONObject(0).getString("sub_dep"),
                json.optString("icon_profile")
        );
    }

    private static class Regexp {

        Pattern pattern;

        Regexp(String pattern) {
            this.pattern = Pattern.compile(pattern, Pattern.DOTALL);
        }

        List<String> matches(String body) {
            List<String> matches = new ArrayList<>();
            var matcher = pattern.matcher(body);
            while (matcher.find()) {
                matches.add(matcher.group(1));
            }
            return matches;
        }
    }

    private RestTemplate restTemplate() throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .useSystemProperties()
                .disableRedirectHandling()
                .disableAuthCaching()
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15")
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);

        return restTemplate;
    }

    private Map<String, String> cookies = new HashMap<>();

    private String step1() throws Exception {
        var lk = restTemplate().exchange("https://lk.spbstu.ru", HttpMethod.GET, null, String.class);
        var lkCookie = lk.getHeaders().getFirst("Set-Cookie").split(";")[0];
        cookies.put("lk.spbstu.ru", lkCookie);

        var clientId = new Regexp("client_id=(.*?)\\&amp;").matches(lk.getBody()).get(0);
        var checkKey  = new Regexp("check_key%253D(.*?)%26mode").matches(lk.getBody()).get(0);

        return step2(clientId, checkKey);
    }

    private String step2(String clientId, String checkKey) throws Exception {
        var casURL = "https://cas.spbstu.ru/oauth2.0/authorize?client_id="+ clientId +
                "&redirect_uri=http%3A%2F%2Flk.spbstu.ru%2Flocal%2Foauth%2Fspbstu_cas.php&response_type=code" +
                "&state=site_id%3Ds1%26backurl%3D%252Findex.php%253Fcheck_key%253D"+ checkKey +
                "%26mode%3Dopener%26redirect_url%3D%252Findex.php";

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.add("Accept-Language", "ru");
        headers.add("Referer", "https://lk.spbstu.ru/");
        var req = new HttpEntity(headers);
        var cas = restTemplate().exchange(casURL, HttpMethod.GET, req, String.class);

        var casCookie = cas.getHeaders().getFirst("Set-Cookie").split(";")[0];

        var location = cas.getHeaders().getFirst("Location");

        cookies.put("cas.spbstu.ru", casCookie);

        return step3(location);
    }

    private String step3(String location) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.add("Referer", "https://lk.spbstu.ru/");
        headers.add("Accept-Language", "ru");
        headers.add("Cookie", cookies.get("cas.spbstu.ru"));
        var req = new HttpEntity(headers);
        var cas = restTemplate().exchange(location, HttpMethod.GET, req, String.class);

        var body = cas.getBody();

        var execution = new Regexp("name=\\\"execution\\\" value=\\\"(.*?)\\\"/>").matches(body).get(0);

        return step4(location, execution);
    }

    private String step4(String location, String execution) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.add("Origin", "https://cas.spbstu.ru");
        headers.add("Referer", location);
        headers.add("Accept-Language", "ru");
        headers.add("Cookie", cookies.get("cas.spbstu.ru"));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", login);
        params.add("password", pass);
        params.add("execution", execution);
        params.add("_eventId", "submit");

        var req = new HttpEntity(params, headers);
        var cas = restTemplate().exchange(location, HttpMethod.POST, req, String.class);

        var tgcCookie = cas.getHeaders().getFirst("Set-Cookie").split(";")[0];

        var casCookies = cookies.get("cas.spbstu.ru") + "; " + tgcCookie;

        cookies.put("cas.spbstu.ru", casCookies);

        var redirectLocation =  cas.getHeaders().getFirst("Location");
        return step5(redirectLocation, location);
    }

    private String step5(String location, String referer) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.add("Origin", "https://cas.spbstu.ru");
        headers.add("Referer", referer);
        headers.add("Accept-Language", "ru");
        headers.add("Cookie", cookies.get("cas.spbstu.ru"));

        var req = new HttpEntity(headers);
        var cas = restTemplate().exchange(location, HttpMethod.GET, req, String.class);
        var redirectLocation =  cas.getHeaders().getFirst("Location");
        return step6(redirectLocation, referer);
    }

    private String step6(String location, String referer) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.add("Origin", "https://cas.spbstu.ru");
        headers.add("Referer", referer);
        headers.add("Accept-Language", "ru");
        headers.add("Cookie", cookies.get("cas.spbstu.ru"));

        var req = new HttpEntity(headers);
        var cas = restTemplate().exchange(location, HttpMethod.GET, req, String.class);
        var redirectLocation =  cas.getHeaders().getFirst("Location");

        return cookies.get("cas.spbstu.ru");
    }

    private String fetchUserBody(String cookie) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.add("Accept-Language", "ru");
        headers.add("Cookie", cookie);

        var req = new HttpEntity(headers);
        var cas = restTemplate().exchange("https://cas.spbstu.ru/login", HttpMethod.GET, req, String.class);

        return cas.getBody();
    }

}