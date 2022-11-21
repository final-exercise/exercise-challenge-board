package com.ssafy.ssafit.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserBmiDto {

	double userWeight;
	double userBmi;
	String createdAt;
}
