package com.ssafy.ssafit.model.dao;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dto.Coach.CoachDto;
import com.ssafy.ssafit.model.dto.User.UserDto;

public interface CoachDao {
	public int insertCoach(CoachDto coachDto);
	public CoachDto selectCoachByCoachId(String id);
	public CoachDto selectCoach(int coachSeq);
	public int deleteCoach(int coachSeq);
	public int updateCoach(CoachDto coachDto);
	public Page<UserDto> selectManageUser(int coachSeq);
}
