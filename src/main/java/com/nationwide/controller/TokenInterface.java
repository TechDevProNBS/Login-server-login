package com.nationwide.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nationwide.dto.ResponseTokenDto;

@FeignClient("token")
public interface TokenInterface {

	@RequestMapping(path = "/api/token/{usernameId}", method = {RequestMethod.POST})
	public ResponseTokenDto createAuthToken(@PathVariable("usernameId") String usernameId);
	
	@RequestMapping(path = "/api/token/{bearerToken}", method = {RequestMethod.GET})
	public ResponseTokenDto authenticate(@PathVariable("bearerToken") String bearerToken);
	
	@RequestMapping(path = "/api/token/{usernameId}", method = {RequestMethod.DELETE})
	public String deleteAllAuthToken(@PathVariable("usernameId") String usernameId);
	
}
