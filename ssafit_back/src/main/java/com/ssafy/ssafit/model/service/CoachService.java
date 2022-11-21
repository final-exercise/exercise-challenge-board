package com.ssafy.ssafit.model.service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Coach.CoachDto;
import com.ssafy.ssafit.model.dto.User.UserDto;

public interface CoachService {
	public int signup(CoachDto coachDto) throws BaseException;
	
	public CoachDto login(CoachDto coachDto) throws BaseException;
	
	public CoachDto getCoach(int coachSeq) throws BaseException;
	
	public int deleteCoach(int cocachSeq) throws BaseException;
	
	public int modifyCoach(CoachDto coachDto) throws BaseException;
	
	public Page<UserDto> getManageUser(int coachSeq) throws BaseException;
	
	
}
