package com.payeshgaran.bootstrap;

import com.payeshgaran.entity.User;
import com.payeshgaran.entity.permission.Role;
import com.payeshgaran.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.payeshgaran.entity.permission.Role.ADMIN;
import static com.payeshgaran.entity.permission.Role.USER;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User admin = User.builder()
                .username("admin")
                .password("1")
                .roles(Set.of(ADMIN))
                .isEnable(true)
                .build();
        User user =  User.builder()
                .username("user")
                .password("1")
                .roles(Set.of(USER))
                .isEnable(true)
                .build();

        userRepository.saveAll(List.of(admin,user));
    }
}
