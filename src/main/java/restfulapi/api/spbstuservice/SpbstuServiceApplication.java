package restfulapi.api.spbstuservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.net.http.HttpHeaders;
import java.util.Map;

@SpringBootApplication
public class SpbstuServiceApplication {


    private static Logger logger = LoggerFactory.getLogger(SpbstuServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpbstuServiceApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner() {
            return (args) -> {
                logger.info("ЗАПУСК СИСТЕМЫ!");
                HttpEntity entity = new HttpEntity(null,null);
                try(FileWriter writer = new FileWriter("groupsSpbstu.txt")) {
                    logger.info("ФАЙЛ СОЗДАН!");
                    for(int i = 31000; i < 35000;++i){
                    ResponseEntity<String> someshit = new RestTemplate().exchange("https://ruz.spbstu.ru/api/v1/ruz/scheduler/"+i,
                            HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                            });
                    JSONObject jsonObject = new JSONObject(someshit.getBody());
                    if (!jsonObject.optBoolean("error")) {
                        JSONObject group = jsonObject.optJSONObject("group");
                        String groupId = group.optString("id");
                        String groupName = group.optString("name");
                        logger.info("id: " + groupId + " name: " + groupName);
                        writer.write("id: " + groupId + " name: " + groupName +  "\n");
                    } else {
                        logger.info("ошибка, группы с id:" + i +"не существует");
                        writer.write("ошибка, группы с id:" + i +"не существует\n");
                    }
                    Thread.sleep(10);
                }
                    writer.flush();
                }
    };
}
}
