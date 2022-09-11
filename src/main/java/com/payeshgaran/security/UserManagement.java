package com.payeshgaran.security;

import com.payeshgaran.entity.Account;
import com.payeshgaran.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserManagement implements UserDetailsService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Account account = accountDao.findByAccountNumber(accountNumber);
        CustomUserDetails customUserDetails = new CustomUserDetails(account);
        return customUserDetails;
    }
}
