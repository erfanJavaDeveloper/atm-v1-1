package com.payeshgaran.entity;


import com.payeshgaran.entity.permission.Role;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
//todo  why when use @NoArgsConstructor can't run application ¯\_(ツ)_/¯
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountNumber;
    private String pin;
    private BigInteger balance;
    private Boolean isEnable;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private int incorrectAttempts = 0;

    @Enumerated(EnumType.STRING)
    private TypeOfAccount type = TypeOfAccount.LOANS;

    @Enumerated(EnumType.STRING)
    private Locked locked = Locked.ENABLE;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate = new Date();

    @Basic
    @Temporal(TemporalType.TIME)
    private java.util.Date utilTime = new Date();


    public Account(String accountNumber, String pin, BigInteger balance, TypeOfAccount type) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.type = type;
    }

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Transaction> transaction =new ArrayList<>() ;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public Account() {
    }

    // amirhossein
    public void addTransaction(Transaction trx) {
        transaction.add(trx);
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream().flatMap(r -> r.getAuthority().stream())
                .collect(Collectors.toSet());

    }

}
