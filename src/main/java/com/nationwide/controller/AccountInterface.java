package com.nationwide.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nationwide.dto.RequestAccountDto;
import com.nationwide.dto.ResponseAccountDto;

@FeignClient("account")
public interface AccountInterface {

	@RequestMapping(path = "/api/account/{username}", method = {RequestMethod.GET})
	public Long getIdByUsername(@PathVariable("username") String username);
	
	@RequestMapping(path = "/api/account/id/{userId}", method = {RequestMethod.GET})
	public String getUsernameById(@PathVariable("userId") Long userId);

	@RequestMapping(path = "/api/account/new", method = {RequestMethod.POST})
	public ResponseAccountDto addUser(@RequestBody RequestAccountDto accountDto);

	@RequestMapping(path = "/api/account", method = {RequestMethod.POST})
	public ResponseAccountDto authenticateUser(@RequestBody RequestAccountDto accountDto);

	@RequestMapping(path = "/api/account/{username}", method = {RequestMethod.PUT})
	public ResponseAccountDto updateUser(@PathVariable("username") String username, @RequestBody RequestAccountDto accountDto);

	@RequestMapping(path = "/api/account/{username}", method = {RequestMethod.DELETE})
	public ResponseAccountDto deleteUser(@PathVariable("username") String username);
}
