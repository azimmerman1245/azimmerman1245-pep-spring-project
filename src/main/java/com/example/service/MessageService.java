package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    // Use @Service and @Transactional (rollbackFor = specifyException.class) annotations
    // Use @Autowired on the singleton instance
    // Consult Spring JPA CRUD & JPA Multiplicity Coding Labs on implementing the Service Class using Optional operations

    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message persistAccount(Message account) {
        return messageRepository.save(account);
    }

    public Message createMessage(int postedBy, String messageText) {
        Optional<Account> optionalAccount = accountRepository.findById(postedBy);
        if (messageText != "" && messageText.length() <= 255 && optionalAccount.isPresent()) {
            return messageRepository.createMessage(postedBy, messageText);
        } else {
            return null;
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            return optionalMessage.get();
        } else {
            return null;
        }
    }

    public int deleteMessageById(int messageId) {
        return messageRepository.deleteMessageById(messageId);
    }

    public int updateMessageById(int messageId, String messageText) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent() && messageText != "" && messageText.length() <= 255) {
            return messageRepository.updateMessageById(messageText, messageId);
        } else {
            return 0;
        }
    }

    public List<Message> getMessagesByPostedBy(int postedBy) {
        return messageRepository.getMessageByPostedBy(postedBy);
    }
}
