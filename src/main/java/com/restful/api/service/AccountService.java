package com.restful.api.service;

import com.restful.api.dto.JwtRequest;
import com.restful.api.response.AccountResponse;

import java.util.List;

public interface AccountService {
   List<String> getRoleByUsernameAndPassword(JwtRequest jwtRequest);
   AccountResponse getAccountByUsernameAndPassword(String username);
    //Account findByName(String username);
}
