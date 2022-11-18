package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
	private int userSeq;
	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
	private String userBirth;
	private String userAddress;
	private String userCreatedAt;
	private String userUpdatedAt;
}
