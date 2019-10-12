package com.restful.api.repository;

import com.restful.api.model.Account;
import com.restful.api.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT role" +
            " FROM account WHERE username = :name and password = :password", nativeQuery = true)
    List<String> getRoleByUsernameAndPassword(@Param("name") String username, @Param("password") String password);

    @Query(value = "SELECT token_device" +
            " FROM account WHERE username = :name", nativeQuery = true)
    String getTokenDeviceByAccountName(@Param("name") String username);

    @Query(value = "Update Account Set token_device = '' Where username = :name", nativeQuery = true)
    void deleteTokenDevice(@Param("name") String username);

    AccountResponse findByUsername(String username);

    @Query(value = "SELECT username, role, password, token_device" +
            " FROM account WHERE username = :name", nativeQuery = true)
     Account findAccountByName(@Param("name") String username);

    @Query(value = "SELECT role, username" +
            " FROM account WHERE username = :name", nativeQuery = true)
    AccountResponse getAccountByUsernameAndPassword(@Param("name") String username);
}
