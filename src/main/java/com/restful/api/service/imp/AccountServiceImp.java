package com.restful.api.service.imp;

import com.restful.api.dto.JwtRequest;
import com.restful.api.repository.AccountRepository;
import com.restful.api.response.AccountResponse;
import com.restful.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<String> getRoleByUsernameAndPassword(JwtRequest accountDetail) {

        return accountRepository.getRoleByUsernameAndPassword(accountDetail.getUsername(),accountDetail.getPassword());
    }

    @Override
    public AccountResponse getAccountByUsernameAndPassword(String username) {

        return accountRepository.getAccountByUsernameAndPassword(username);
    }

}
