package ma.bank.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.bank.accountservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")

public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomers")
    List<Customer> getCustomers();

    default Customer getDefaultCustomer(Long id , Exception e) {
        return Customer.builder()
                .id(id)
                .firstName("NULL")
                .lastName("NULL")
                .build();
    }
    default List<Customer> getDefaultCustomers(Exception e) {
        return List.of();
    }


}
