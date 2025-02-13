package com.example.service;

import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.entity.Account;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
        try {
            if (message.getMessageText() != "" && message.getMessageText().length() <= 255 && accountRepository.findAccountByAccountId(message.getPostedBy()) != null) {
                persistMessage(message);
                return messageRepository.findMessageByMessageId(message.getMessageId());
            } else {
                throw new ClientErrorException();
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        try {
            return messageRepository.findMessageByMessageId(messageId);
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }

    @Transactional
    public int deleteMessageById(int messageId) {
        try {
            if (messageRepository.findMessageByMessageId(messageId) != null) {
                messageRepository.deleteByMessageId(messageId);
                return 1;
            }
            return 0;
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }

    public int updateMessageById(String messageText, int messageId) {
        try {
            if (messageRepository.findMessageByMessageId(messageId) != null && messageText.length() <= 255 && messageText != "") {
                Message updatedMessage = messageRepository.findById(messageId).get();
                updatedMessage.setMessageText(messageText);
                messageRepository.save(updatedMessage);
                return 1;
            } else {
                throw new ClientErrorException();
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }

    public List<Message> getMessagesByPostedBy(int postedBy) {
        return messageRepository.findMessagesByPostedBy(postedBy);
    }
}
