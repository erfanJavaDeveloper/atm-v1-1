package com.payeshgaran.config;

import com.payeshgaran.security.UserManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.payeshgaran.entity.permission.Role.ADMIN;
import static com.payeshgaran.entity.permission.Role.USER;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserManagement userManagement;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .csrf().disable()
                .authorizeRequests()
//                                                 *******accounts
//                .antMatchers(HttpMethod.GET, "/accounts/**").hasAnyRole(USER.name(), ADMIN.name())
//                .antMatchers(HttpMethod.POST, "/accounts/**").hasAnyRole(ADMIN.name())
//                .antMatchers(HttpMethod.PUT, "/accounts/**").hasAnyRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE, "/accounts/**").hasAnyRole(ADMIN.name())
//                .antMatchers(HttpMethod.GET, "/transacts/**").hasAnyRole(USER.name(), ADMIN.name())
//                .antMatchers(HttpMethod.POST, "/transacts/**").hasAnyRole(USER.name(), ADMIN.name())
//                .antMatchers(HttpMethod.PUT, "/transacts/**").hasAnyRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE, "/transacts/**").hasAnyRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST, "/account/**").hasAnyAuthority( WRITE_USER.getPermissionName() )
//                .antMatchers(HttpMethod.PUT, "/account/**").hasAnyAuthority(READ_USER.getPermissionName(), WRITE_USER.getPermissionName() )
//                .antMatchers(HttpMethod.DELETE, "/account/**").hasAnyAuthority(READ_USER.getPermissionName(), WRITE_USER.getPermissionName() )
//                *******

//                .antMatchers
//                        ("/")
//                .permitAll()
                .anyRequest()
                .authenticated()
//                .permitAll()

                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().disable();
//                .and()
//                .formLogin()
//                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userManagement);
        return provider;
    }

    //todo ******************************
    //todo inmemory userditailService
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails erfan = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("123"))
//                .authorities(USER.getAuthority())
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("123"))
//                .authorities(ADMIN.getAuthority())
//                .build();
//
//        return new InMemoryUserDetailsManager(erfan, admin);
//    }
}
