package com.example.service;

import com.example.entity.Account;
import com.example.exception.ClientErrorException;
import com.example.exception.UnauthorizedUserException;
import com.example.exception.UserAlreadyExistsException;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    // Use @Service and @Transactional (rollbackFor = specifyException.class) annotations
    // Use @Autowired for singleton instance
    // Consult Spring JPA CRUD & JPA Multiplicity Coding Labs on implementing the Service Class using Optional operations

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account persistAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account createAccount(Account account, String username, String password) {
        try {
            if (username == "" || password.length() < 4) {
                throw new ClientErrorException();
            } else if (accountRepository.findAccountByUsername(username) != null) {
                throw new UserAlreadyExistsException();
            } else {
                persistAccount(account);
                return accountRepository.findAccountByUsername(username);
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }

    public Account loginAccount(String username, String password) {
        try {
            if (accountRepository.findAccountByUsernameAndPassword(username, password) == null) {
                throw new UnauthorizedUserException();
            } else {
                return accountRepository.findAccountByUsernameAndPassword(username, password);
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }
}
