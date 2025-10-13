package ma.bank.accountservice.controller;


import ma.bank.accountservice.clients.CustomerRestClient;
import ma.bank.accountservice.entities.BankAccount;
import ma.bank.accountservice.entities.Customer;
import ma.bank.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    private  AccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountController(AccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }


    @GetMapping("/accounts/{accountId}")
    public ResponseEntity findAccountById(@PathVariable String accountId) {
        BankAccount  account= accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        Customer customer=customerRestClient.getCustomerById(account.getCustomerId());
        account.setCustomer(customer);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/accounts")
    public ResponseEntity findAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

}
