package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
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
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message persistAccount(Message account) {
        return messageRepository.save(account);
    }
}
