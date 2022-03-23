package restfulapi.api.spbstuservice;

import io.netty.internal.tcnative.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import restfulapi.api.spbstuservice.Services.importLessons.ImportService;

import java.io.FileWriter;
import java.net.http.HttpHeaders;
import java.util.Map;

@SpringBootApplication
@EntityScan(basePackages = "restfulapi.api.spbstuservice")
@EnableJpaRepositories(basePackages = "restfulapi.api.spbstuservice")
@Configuration
public class SpbstuServiceApplication {

    public static Logger logger = LoggerFactory.getLogger(SpbstuServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpbstuServiceApplication.class, args);

    }


    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ImportService importService) {
            return (args) -> {
                logger.info("ЗАПУСК СИСТЕМЫ!");
                importService.importTeachers();
                importService.importGroups();
            };
}
}
