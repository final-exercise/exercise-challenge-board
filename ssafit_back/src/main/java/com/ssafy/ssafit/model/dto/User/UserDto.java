package com.ssafy.ssafit.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private int userSeq;
	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
	private String userBirth;
	private String userNickname;
	private String userGender;
	private String userCreatedAt;
	private String userUpdatedAt;
}
