package restfulapi.api.spbstuservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import restfulapi.api.spbstuservice.Services.importLessons.ImportService;

@SpringBootApplication
@Configuration
@EnableEurekaClient
@EnableScheduling
public class SpbstuServiceApplication {

    public static Logger logger = LoggerFactory.getLogger(SpbstuServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpbstuServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ImportService importService) {
            return (args) -> {
                logger.info("ЗАПУСК СИСТЕМЫ!");
                /* importService.importBuildings();
                importService.importTeachers();
                importService.importGroups(); */
            };
}

















}
