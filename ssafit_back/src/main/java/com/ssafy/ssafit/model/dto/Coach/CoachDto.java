package com.ssafy.ssafit.model.dto.Coach;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {
	private int coachSeq;
	private String coachId;
	private String coachPassword;
	private String coachName;
	private String coachEmail;
	private String coachBirth;
	private String coachAddress;
	private String coachNickname;
	private String coachGender;
	private String coachProfileImgUrl;
	private String coachCreatedAt;
	private String coachUpdatedAt;
}
