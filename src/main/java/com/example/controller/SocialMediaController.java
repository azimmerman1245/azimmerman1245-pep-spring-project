package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.exception.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    // @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
    // ResponseEntity.status(statusNumber).Body("additionalMessageInBody")
    // Can also use @RequestBody annotation to pull out of requestbody or add one
    // Consult Spring Rest Controller, Spring Request Param, and Spring Response Entity Labs for specifics

    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Account createAccount(@RequestBody Account account) {
        if (account.getUsername() == "" || account.getPassword().length() < 4) {
            ResponseEntity.status(400);
        }
        Account newAccount = accountService.createAccount(account);
        if (newAccount == null) {
            ResponseEntity.status(409);
        }
        return newAccount;
    }

}
