package com.restful.api.repository;

import com.restful.api.dto.AccountResponseDTO;
import com.restful.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
//    @Query(value = "SELECT role, name" +
//            " FROM account WHERE name = :name and password = :password", nativeQuery = true)
//    AccountResponseDTO getAccountByUsernameAndPassword(@Param("name") String username, @Param("password") String password);

    Account findByName(String username);

//    @Query(value = "SELECT role, name" +
//            " FROM account WHERE name = :name", nativeQuery = true)
//    AccountResponseDTO getAccountByUsername(@Param("name") String username);

}
