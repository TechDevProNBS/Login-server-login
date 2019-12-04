package com.nationwide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nationwide.dto.RequestAccountChangeDto;
import com.nationwide.dto.RequestAccountDto;
import com.nationwide.dto.ResponseLoginDto;
import com.nationwide.service.LoginService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/login")
public class LoginController {

	@Autowired
	private LoginService service;
	
	@PostMapping("/register")
	public ResponseLoginDto createAccount(@RequestBody RequestAccountDto accountDto) {
		return service.createAccount(accountDto);
	}
	
	@DeleteMapping
	public String deleteAccount(@RequestBody RequestAccountDto accountDto) {
		service.deleteAccount(accountDto);
		return "Account deleted";
	}
	
	@PutMapping
	public String updateAccount(@RequestBody RequestAccountChangeDto accountChangeDto) {
		RequestAccountDto previous = new RequestAccountDto();
		previous.setUsername(accountChangeDto.getOldUsername());
		previous.setPassword(accountChangeDto.getOldPassword());
		
		RequestAccountDto after = new RequestAccountDto();
		after.setUsername(accountChangeDto.getNewUsername());
		after.setPassword(accountChangeDto.getNewPassword());
		service.updateAccount(previous, after);
		return "Account updated";
	}
	
	@PostMapping
	public ResponseLoginDto authenticate(@RequestBody RequestAccountDto accountDto) {
		return service.authenticate(accountDto);
	}
	
	@GetMapping("/{bearerToken}")
	public ResponseLoginDto authenticateWithToken(@PathVariable String bearerToken) {
		return service.authenticateWithToken(bearerToken);
	}
	
}
