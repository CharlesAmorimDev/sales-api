package org.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesApplication {
/*
    @Bean
    public CommandLineRunner execute(
            @Autowired CustomerRepository customerRepository) {
        return args -> {
            Customer customer = new Customer("charles@email.com", "123", "ADMIN", "Charles", "22/02/2022", "21538127040", "11 - 9 9999-9999", "Rua direita, 145");
            customerRepository.save(customer);
        };
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
