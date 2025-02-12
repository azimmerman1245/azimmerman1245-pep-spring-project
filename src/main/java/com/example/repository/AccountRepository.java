package com.example.repository;

import com.example.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    // Use @Query to write Query statements with :variable as a parametrized variable that can be
    // set using @Param("variable") DataType variable;
    // Use named Queries? Check Named Queries Coding Challenge

     /**
      * This Query retrieves an account from the database with a givern username and password
      * @param username given username
      * @param password given password
      * @return an account if username and password match, or null if they do not
      */
    Account findAccountByUsernameAndPassword(String username, String password);

    /**
     * This Query is supplemental for createAccount and retrieves an account by their username
     * @param username given username
     * @return the account retrieved
     */
    Account findAccountByUsername(String username);
    

}
