package com.restful.api.controller;

import com.restful.api.config.JwtTokenUtil;
import com.restful.api.dto.AccountDTO;
import com.restful.api.dto.JwtRequest;
import com.restful.api.model.JwtResponse;
import com.restful.api.response.AccountResponse;
import com.restful.api.service.AccountService;
import com.restful.api.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        List<String> role = accountService.getRoleByUsernameAndPassword(authenticationRequest);

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername(), role);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/me/{account_name}", method = RequestMethod.GET)
    private @ResponseBody
    AccountResponse getAccountByUsernameAndPassword(@PathVariable(value = "account_name") String account_name){

        return accountService.getAccountByUsernameAndPassword(account_name);
    }

    @RequestMapping(value = "/token_device", method = RequestMethod.POST)
    public @ResponseBody void saveOrUpdateTokenDevice(@RequestBody AccountDTO accountDTO){
        accountService.saveOrUpdateTokenDevice(accountDTO);
    }

    @RequestMapping(value = "/token_device/{name}", method = RequestMethod.DELETE)
    public @ResponseBody void  deleteTokenDevice(@PathVariable(value = "name") String account_name){
        accountService.deleteTokenDevice(account_name);
    }
}
