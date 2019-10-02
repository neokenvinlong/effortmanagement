//package com.restful.api.service.imp;
//
//import com.restful.api.dto.AccontResponseDTO;
//import com.restful.api.dto.JwtRequest;
//import com.restful.api.exception.ResourceNotFoundException;
//import com.restful.api.model.Account;
//import com.restful.api.repository.AccountRepository;
//import com.restful.api.service.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AccountServiceImp implements AccountService {
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Override
//    public AccontResponseDTO getRoleByUsernameAndPassword(JwtRequest accountDetail) {
//
//        return accountRepository.getAccountByUsernameAndPassword(accountDetail.getUsername(),accountDetail.getPassword());
//    }
//
////    @Override
////    public Account findByName(String username) {
////
////        return accountRepository.findByName(username);
////    }
//}
