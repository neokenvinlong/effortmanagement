package com.restful.api.service;

import com.restful.api.dto.AccountDTO;
import com.restful.api.dto.JwtRequest;
import com.restful.api.model.Account;
import com.restful.api.response.AccountResponse;

import java.util.List;

public interface AccountService {
   List<String> getRoleByUsernameAndPassword(JwtRequest jwtRequest);
   void saveOrUpdateTokenDevice(AccountDTO account);
   void deleteTokenDevice(String name);
    AccountResponse getAccountByUsernameAndPassword(String username);
    String getTokenDeviceByAccountName(String account_name);
    //Account findByName(String username);
}
