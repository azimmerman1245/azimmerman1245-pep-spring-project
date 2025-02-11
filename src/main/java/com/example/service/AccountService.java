package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    // Use @Service and @Transactional (rollbackFor = specifyException.class) annotations
    // Use @Autowired for singleton instance
    // Consult Spring JPA CRUD & JPA Multiplicity Coding Labs on implementing the Service Class using Optional operations

    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account persistAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account createAccount(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findById(accountRepository.findAccountByUsername(username).getAccountId());
        if(optionalAccount.isEmpty() && username != "" && password.length() > 3) {
            return accountRepository.createAccount(username, password);
        } else {
            return null;
        }
    }

    public Account loginAccount(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findById(accountRepository.loginAccount(username, password).getAccountId());
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            return null;
        }
    }
}
