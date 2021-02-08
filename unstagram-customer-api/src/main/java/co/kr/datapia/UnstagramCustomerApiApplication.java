package co.kr.datapia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UnstagramCustomerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnstagramCustomerApiApplication.class, args);
    }

}
