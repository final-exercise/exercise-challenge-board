package com.ssafy.ssafit.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWorkoutDto {
	int userSeq;
	int userWorkoutSeq;
	int videoSeq;
	String videoTitle;
	int videoCal;
	String createdAt;
}
