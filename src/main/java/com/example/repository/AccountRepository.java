package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    // Use @Query to write Query statements with :variable as a parametrized variable that can be
    // set using @Param("variable") DataType variable;
    // Use named Queries? Check Named Queries Coding Challenge

    /**
     * This Query inserts an account with a given username and password
     * 
     * @param username given username
     * @param password given password
     * @return the account created
     */

     @Query("INSERT INTO account (username, password) VALUES (:usernameVar, :passwordVar)")
     Account createAccount(@Param("usernameVar") String username, @Param("passwordVar") String password);

     /**
      * This Query retrieves an account from the database with a givern username and password
      * @param username given username
      * @param password given password
      * @return an account if username and password match, or null if they do not
      */

      @Query("SELECT * FROM account WHERE username = :usernameVar AND password = :passwordVar")
      Account loginAccount(@Param("usernameVar") String username, @Param("passwordVar") String password);

}
