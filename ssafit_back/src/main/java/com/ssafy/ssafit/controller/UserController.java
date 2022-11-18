package com.ssafy.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.UserDto;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;

@RequestMapping("/user")
@RestController
@Api("User")
public class UserController {
	
	private JwtUtil jwtUtil;
	
	@Autowired
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
//	@GetMapping("login")
//	public ResponseEntity<?> login(UserDto user) throws BaseException{
//		try {
//			
//		} catch(Exception e){
//			throw new BaseException(false, 500, "login error");
//		}
//	}
//	
//	@GetMapping("/")
//	public ResponseEntity<UserDto> getUser(){
//		
//	}
	
}
