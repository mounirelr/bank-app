package ma.bank.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Double balance;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private AccountType bankAccount;
    private String currency;
    private Long customerId;
    @Transient
    private Customer customer;

}
