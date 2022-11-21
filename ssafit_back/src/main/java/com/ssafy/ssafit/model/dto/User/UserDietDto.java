package com.ssafy.ssafit.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDietDto {
	int userSeq;
	String createdAt;
	int userDietSeq;
	int dietSeq;
	String dietName;
	String dietImgUrl;
	int dietCal;
}
