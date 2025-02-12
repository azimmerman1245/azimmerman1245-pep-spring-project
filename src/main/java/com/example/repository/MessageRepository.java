package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    // Use @Query to write Query statements with :variable as a parametrized variable that can be
    // set using @Param("variable") DataType variable;
    // Use named Queries? Check Named Queries Coding Challenge

    /**
     * This Query returns a list of all messages in the database
     * @return List of all messages
     */
    List<Message> findAll();

    /**
     * This Query retrieves a message by its messageId
     * @param messageId
     * @return message whose messageId matches the given request
     */
    Message findMessageByMessageId(int messageId);

    /**
     * This Query deletes a message specified by the messageId and returns the number of rows affected as an integer
     * @param messageId
     * @return number of rows affected
     */
    void deleteByMessageId(int messageId);

    /**
     * This Query returns a list of messages posted by a given user
     * @param postedBy
     * @return a list of messages
     */
    List<Message> findMessagesByPostedBy(int postedBy);
}
