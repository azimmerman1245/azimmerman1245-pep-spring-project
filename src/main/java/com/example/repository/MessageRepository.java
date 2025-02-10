package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    // Use @Query to write Query statements with :variable as a parametrized variable that can be
    // set using @Param("variable") DataType variable;
    // Use named Queries? Check Named Queries Coding Challenge
}
