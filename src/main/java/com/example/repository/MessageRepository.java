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
     * This Query will create a message with the given FK postedBy and message text
     * @param postedBy the userID of the account that posted the message
     * @param messageText text of the message to be created
     * @return the message populated to the database
     */
    @Query("INSERT INTO message (postedBy, messageText) VALUES (:postedByVar, :messageTextVar)")
    Message createMessage(@Param("postedByVar") int postedBy, @Param("messageTextVar") String messageText);

    /**
     * This Query returns a list of all messages in the database
     * @return List of all messages
     */
    @Query("SELECT * FROM message")
    List<Message> getAllMessages();

    /**
     * This Query retrieves a message by its messageId
     * @param messageId
     * @return message whose messageId matches the given request
     */
    @Query("SELECT * FROM message WHERE messageId = :messageIdVar")
    Message getMessageById(@Param("messageIdVar") int messageId);

    /**
     * This Query deletes a message specified by the messageId and returns the number of rows affected as an integer
     * @param messageId
     * @return number of rows affected
     */
    @Modifying
    @Query("DELETE * FROM message WHERE messageId = :messageIdVar")
    Integer deleteMessageById(@Param("messageIdVar") int messageId);

    /**
     * This Query updates the messageText of a message with a given messageId
     * @param messageId
     * @param messageText
     * @return updated message
     */
    @Modifying
    @Query("UPDATE message SET messageText = :messageTextVar WHERE messageId = :messageIdVar")
    Integer updateMessageById(@Param("messageTextVar") String messageText, @Param("messageIdVar") int messageId);

    /**
     * This Query returns a list of messages posted by a given user
     * @param postedBy
     * @return a list of messages
     */
    @Query("SELECT * FROM message WHERE postedBy = :postedByVar")
    List<Message> getMessageByPostedBy(@Param("postedByVar") int postedBy);
}
