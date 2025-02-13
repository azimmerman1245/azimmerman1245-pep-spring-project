package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

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
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        Account newAccount = accountService.createAccount(account, username, password);

        return ResponseEntity.ok(newAccount);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginAccount(@RequestBody Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        Account checkedAccount = accountService.loginAccount(username, password);

        return ResponseEntity.ok(checkedAccount);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        Message checkedMessage = messageService.createMessage(message);

        return ResponseEntity.ok(checkedMessage);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessageById(@PathVariable int messageId) {
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMessageById(@PathVariable int messageId) {
        if (messageService.deleteMessageById(messageId) == 1) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateMessageById(@PathVariable int messageId, @RequestBody Message message) {
        String messageText = message.getMessageText();
        return ResponseEntity.ok(messageService.updateMessageById(messageText, messageId));
    }

    @RequestMapping(value = "/accounts/{accountId}/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getMessageByAccountId(@PathVariable int accountId) {
        return ResponseEntity.ok(messageService.getMessagesByPostedBy(accountId));
    }

}
