package com.ssafy.ssafit.model.dto.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserActivityDto {
	int userSeq;
	String userNickname;
	String key;
	int value;
}
