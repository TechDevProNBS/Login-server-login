package com.nationwide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nationwide.controller.AccountInterface;
import com.nationwide.controller.TokenInterface;
import com.nationwide.dto.RequestAccountDto;
import com.nationwide.dto.ResponseAccountDto;
import com.nationwide.dto.ResponseLoginDto;
import com.nationwide.dto.ResponseTokenDto;

@Service
public class LoginService {
	
	@Autowired
	private AccountInterface accountInterface;
	
	@Autowired
	private TokenInterface tokenInterface;

	public ResponseLoginDto createAccount(RequestAccountDto accountDto) {
		ResponseAccountDto responseAccountDto = accountInterface.addUser(accountDto);
		ResponseTokenDto token = tokenInterface.createAuthToken(String.valueOf(responseAccountDto.getId()));
		ResponseLoginDto loginDto = new ResponseLoginDto();
		loginDto.setUsername(responseAccountDto.getUsername());
		loginDto.setBearerToken(token.getBearerToken());
		return loginDto;
	}
	
	public void deleteAccount(RequestAccountDto accountDto) {
		accountInterface.authenticateUser(accountDto);
		Long userId = accountInterface.getIdByUsername(accountDto.getUsername());
		accountInterface.deleteUser(accountDto.getUsername());
		tokenInterface.deleteAllAuthToken(String.valueOf(userId));
	}
	
	public void updateAccount(RequestAccountDto previous, RequestAccountDto after) {
		accountInterface.authenticateUser(previous);
		accountInterface.updateUser(previous.getUsername(), after);
	}
	
	public ResponseLoginDto authenticate(RequestAccountDto accountDto) {
		ResponseAccountDto account = accountInterface.authenticateUser(accountDto);
		ResponseTokenDto token = tokenInterface.createAuthToken(String.valueOf(account.getId()));
		ResponseLoginDto response = new ResponseLoginDto();
		response.setUsername(account.getUsername());
		response.setBearerToken(token.getBearerToken());
		return response;
	}
	
	public ResponseLoginDto authenticateWithToken(String bearerToken) {
		ResponseTokenDto token = tokenInterface.authenticate(bearerToken);
		String username = accountInterface.getUsernameById(Long.valueOf(token.getUsernameId()));
		ResponseLoginDto response = new ResponseLoginDto();
		response.setUsername(username);
		response.setBearerToken(token.getBearerToken());
		return response;
	}	
	
	public boolean checkAvailability(String username) {
		return accountInterface.checkAvailability(username);
	}
}
