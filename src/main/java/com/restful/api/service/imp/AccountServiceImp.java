package com.restful.api.service.imp;

import com.restful.api.dto.AccountDTO;
import com.restful.api.dto.JwtRequest;
import com.restful.api.model.Account;
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
    public void saveOrUpdateTokenDevice(AccountDTO accountDto) {
        Account account = accountRepository.findAccountByName(accountDto.getAccount_name());
        account.setToken_device(accountDto.getToken_device());

        accountRepository.save(account);
    }

    @Override
    public void deleteTokenDevice(String username){
        accountRepository.deleteTokenDevice(username);
    }

    @Override
    public AccountResponse getAccountByUsernameAndPassword(String username) {

        return accountRepository.getAccountByUsernameAndPassword(username);
    }

    @Override
    public String getTokenDeviceByAccountName(String account_name) {

        return accountRepository.getTokenDeviceByAccountName(account_name);
    }

}
