package com.payeshgaran.security;

import com.payeshgaran.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomUserDetails implements UserDetails {

    private final String accountNumber;
    private final String pin;
    private final Boolean isEnable;
    private final Boolean isAccountNonExpired;
    private final Boolean isAccountNonLocked;
    private final Boolean isCredentialsNonExpired;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Account account) {

        this.accountNumber = account.getAccountNumber();
        this.pin = account.getPin();
        this.isEnable = account.getIsEnable();
        this.isAccountNonExpired = account.getIsAccountNonExpired();
        this.isAccountNonLocked = account.getIsAccountNonLocked();
        this.isCredentialsNonExpired = account.getIsCredentialsNonExpired();
        this.authorities = account.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return accountNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return isCredentialsNonExpired;
        return isAccountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
