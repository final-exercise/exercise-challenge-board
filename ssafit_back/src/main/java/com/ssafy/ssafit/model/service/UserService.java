package com.ssafy.ssafit.model.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.User.UserActivityDto;
import com.ssafy.ssafit.model.dto.User.UserBmiDto;
import com.ssafy.ssafit.model.dto.User.UserDietDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.User.UserWorkoutDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

public interface UserService {
	public int signup(UserDto userDto) throws BaseException;
	
	public UserDto login(UserDto userDto) throws BaseException;
	
	public UserDto getUser(int userSeq) throws BaseException;
	
	public int deleteUser(int userSeq) throws BaseException;
	
	public int modifyUser(UserDto userDto) throws BaseException;
	
	public List<UserBmiDto> getUserBmi(int userSeq) throws BaseException;
	
	public List<UserWorkoutDto> getUserMonthlyWorkout(int userSeq, int month, int year) throws BaseException;
	
	public int registUserWorkout(UserWorkoutDto userWorkoutDto) throws BaseException;
	
	public List<UserDietDto> getUserMonthlyDiet(int userSeq, int month, int year) throws BaseException;
	
	public int registUserDiet(UserDietDto userDietDto) throws BaseException;
	
	public Page<VideoDto> getWish(int userSeq) throws BaseException;
	
	public Page<UserActivityDto> getUserActivityRank(SearchCondition searchCondition) throws BaseException;
	
	public int modifyUserActivity(UserActivityDto userActivityDto) throws BaseException;
	
	public int getCntByUserNickname(String nickname) throws BaseException;
	
	public int getCntByUserId(String Id) throws BaseException;
	
	public int registerUserBmi(UserBmiDto userBmiDto, int userSeq) throws BaseException;

	public int createUserSubTable(UserDto userDto) throws BaseException;
}
