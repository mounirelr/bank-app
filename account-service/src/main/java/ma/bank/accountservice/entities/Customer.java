package ma.bank.accountservice.entities;



import lombok.*;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;

}
