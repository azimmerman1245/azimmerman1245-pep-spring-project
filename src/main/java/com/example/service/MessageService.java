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

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message persistMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message createMessage(Message message) {
        Optional<Account> optionalAccount = accountRepository.findById(message.getPostedBy());
        if (message.getMessageText() != "" && message.getMessageText().length() <= 255 && optionalAccount.isPresent()) {
            persistMessage(message);
            return messageRepository.findMessageByMessageId(message.getMessageId());
        } else {
            return null;
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
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
        try {
            messageRepository.deleteMessageByMessageId(messageId);
            return 1;
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    public int updateMessageById(Message message) {
        Optional<Message> optionalMessage = messageRepository.findById(message.getMessageId());
        if (optionalMessage.isPresent() && message.getMessageText() != "" && message.getMessageText().length() <= 255) {
            persistMessage(message);
            return 1;
        } else {
            return 0;
        }
    }

    public List<Message> getMessagesByPostedBy(int postedBy) {
        return messageRepository.findMessageByPostedBy(postedBy);
    }
}
