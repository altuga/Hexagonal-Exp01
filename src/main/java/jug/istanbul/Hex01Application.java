package jug.istanbul;

import jug.istanbul.adapter.out.persistence.InMemoryDiscountRateRepository;
import jug.istanbul.domain.port.DiscountRateRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Hex01Application {

    public static void main(String[] args) {
        SpringApplication.run(Hex01Application.class, args);
    }

    @Bean
    public DiscountRateRepository discountRateRepository() {
        return new InMemoryDiscountRateRepository();
    }
}
