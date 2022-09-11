//package com.payeshgaran.bootstrap;
//
//import com.payeshgaran.entity.User;
//import com.payeshgaran.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Set;
//
//import static com.payeshgaran.entity.permission.Role.ADMIN;
//import static com.payeshgaran.entity.permission.Role.USER;
//
//@Component
//@RequiredArgsConstructor
//public class Bootstrap implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("1"))
//                .roles(Set.of(ADMIN))
//                .isAccountNonExpired(true)
//                .isCredentialsNonExpired(true)
//                .isAccountNonLocked(true)
//                .isEnable(true)
//                .build();
//        User user =  User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("1"))
//                .roles(Set.of(USER))
//                .isAccountNonExpired(true)
//                .isCredentialsNonExpired(true)
//                .isAccountNonLocked(true)
//                .isEnable(true)
//                .build();
//
//        userRepository.saveAll(List.of(admin,user));
//    }
//}
