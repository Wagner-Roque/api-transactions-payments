package com.wagner.payment.apipayment.domain.user;

import com.wagner.payment.apipayment.dtos.UserDTO;
import com.wagner.payment.apipayment.enumerator.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserEntity(UserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.email = data.email();

    }
}
