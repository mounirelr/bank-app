package ma.bank.accountservice;

import ma.bank.accountservice.clients.CustomerRestClient;
import ma.bank.accountservice.entities.AccountType;
import ma.bank.accountservice.entities.BankAccount;
import ma.bank.accountservice.entities.Customer;
import ma.bank.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}


	@Bean

	CommandLineRunner  start(AccountRepository  accountRepository , CustomerRestClient customerRestClient){
		return (args) -> {
			List<Customer> allCustomers = customerRestClient.getCustomers();
			allCustomers.forEach(customer -> {
				for(AccountType accountType : AccountType.values()) {
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.balance(Math.random() * 8000)
							.createdAt(LocalDateTime.now())
							.type(accountType)
							.currency("MAD")
							.customerId(customer.getId())
							.build();
					accountRepository.save(bankAccount);
				}
			});

		};
	}

}


