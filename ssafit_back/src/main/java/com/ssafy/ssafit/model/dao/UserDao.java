package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dto.User.UserActivityDto;
import com.ssafy.ssafit.model.dto.User.UserBmiDto;
import com.ssafy.ssafit.model.dto.User.UserDietDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.User.UserWorkoutDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

public interface UserDao {
	public int insertUser(UserDto userDto);
	
	public UserDto selectUserByUserId(String userId);
	
	public UserDto selectUser(int userSeq);
	
	public int selectCntByUserNickname(String userNickname);
	
	public int selectCntByUserId(String userId);
	
	public int deleteUser(int userSeq);
	
	public int updateUser(UserDto userDto);
	
	public int insertUserBmi(Map<String,Object> map);
	
	public List<UserBmiDto> selectUserBmi(int userSeq);
	
	public List<UserWorkoutDto> selectUserWorkout(Map<String, Integer> map);

	public int insertUserWorkout(UserWorkoutDto userWorkoutDto);

	public List<UserDietDto> selectUserDiet(Map<String, Integer> map);

	public int insertUserDiet(UserDietDto userDietDto);

	public Page<VideoDto> selectWishVideo (int userSeq);

	public Page<UserActivityDto> selectUserActivitySort(SearchCondition searchCondition);

	public int updateUserActivity(Map<String, Object> map);
	
	public int insertUserActivity(UserDto userDto);
}
