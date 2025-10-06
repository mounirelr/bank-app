package ma.bank.customerservice;

import ma.bank.customerservice.entities.Customer;
import ma.bank.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner init(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(Customer.builder()
					.firstName("reda")
					.lastName("wahbi")
					.email("wahbi@example.com")
					.build());
			customerRepository.save(Customer.builder()
					.firstName("Amine")
					.lastName("sabiri")
					.email("sabiri@gmail.com")
					.build());
		};
	}

}
