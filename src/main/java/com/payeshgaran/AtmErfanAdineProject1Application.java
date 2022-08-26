package com.payeshgaran;

import com.payeshgaran.entity.Account;
import com.payeshgaran.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.payeshgaran.entity.TypeOfAccount.*;

@SpringBootApplication
//        (exclude = SecurityAutoConfiguration.class)
//@ConfigurationPropertiesScan
public class AtmErfanAdineProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(AtmErfanAdineProject1Application.class, args);


    }


    @Bean
    CommandLineRunner run(AccountService accountService)
    {
        return args -> {
            accountService.saveWithOutDto(new Account("6038","123",BigInteger.valueOf(20030),LONG_TERM ));
            accountService.saveWithOutDto(new Account("6037","123",BigInteger.valueOf(20030), SHORT_TERM ));
            accountService.saveWithOutDto(new Account("6039","123",BigInteger.valueOf(25000),LOANS));

        };
    }


}
