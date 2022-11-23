package com.ssafy.ssafit.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDietDto {
	int userSeq;
	String createdAt;
	int userDietSeq;
	String dietId;
	String dietName;
	String dietMaker;
	int dietCal;
}
