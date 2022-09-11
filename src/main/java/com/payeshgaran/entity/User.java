package com.payeshgaran.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isEnable;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;

//
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @Builder.Default
//    private Set<Role> roles = new HashSet<>();
//
//
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return roles.stream().flatMap(r -> r.getAuthority().stream())
//                .collect(Collectors.toSet());
//
//    }

}
