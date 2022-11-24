package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.config.secret.Secret;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.Coach.CoachDto;
import com.ssafy.ssafit.model.dto.User.UserActivityDto;
import com.ssafy.ssafit.model.dto.User.UserBmiDto;
import com.ssafy.ssafit.model.dto.User.UserDietDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.User.UserWorkoutDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDto;
import com.ssafy.ssafit.util.AES128;

@Service
public class UserServiceImpl implements UserService {
	
	private final boolean FAIL = false;
	private final boolean SUCCESS = true;

	@Autowired
	private UserDao ud;
	
	@Override
	public int signup(UserDto userDto) throws BaseException {
        String pwd;
        
        try{
            //password 암호화
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(userDto.getUserPassword());
            System.out.println(pwd);
            userDto.setUserPassword(pwd);
        } catch (Exception e) {
            throw new BaseException(false,500,"password encrypt error");
        }
        
        try {
        	return ud.insertUser(userDto);
        } catch(Exception e) {
        	e.printStackTrace();
        	throw new BaseException(false, 500, "database error");
        }
	}

	@Override
	public UserDto login(UserDto userDto) throws BaseException {
		try {
			String reqId = userDto.getUserId();
			UserDto resUser = ud.selectUserByUserId(reqId);
			try {
				if(resUser==null 
						|| resUser.getUserId().length()==0 
						|| !new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(resUser.getUserPassword()).equals(userDto.getUserPassword())) {
					return null;
				} else {
					
					return resUser;
				}
			} catch (Exception e) {
				throw new BaseException(FAIL, 500, "decrypt error");
			}
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error" );
		}
	}

	@Override
	public UserDto getUser(int userSeq) throws BaseException {
		try {
			return ud.selectUser(userSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int deleteUser(int userSeq) throws BaseException{
		try {
			return ud.deleteUser(userSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int modifyUser(UserDto userDto) throws BaseException{
		try {
			return ud.updateUser(userDto);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public List<UserBmiDto> getUserBmi(int userSeq) throws BaseException {
		try {
			return ud.selectUserBmi(userSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public List<UserWorkoutDto> getUserMonthlyWorkout(int userSeq, int month, int year) throws BaseException{
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("userSeq", userSeq);
			map.put("month", month);
			map.put("year", year);
			return ud.selectUserWorkout(map);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int registUserWorkout(UserWorkoutDto userWorkoutDto) throws BaseException{
		try {
			return ud.insertUserWorkout(userWorkoutDto);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public List<UserDietDto> getUserMonthlyDiet(int userSeq, int month, int year) throws BaseException{
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("userSeq", userSeq);
			map.put("month", month);
			map.put("year", year);
			return ud.selectUserDiet(map);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int registUserDiet(UserDietDto userDietDto) throws BaseException {
		try {
			System.out.println(userDietDto);
			return ud.insertUserDiet(userDietDto);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public Page<VideoDto> getWish(int userSeq) throws BaseException {
		try {
			return ud.selectWishVideo(userSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public Page<UserActivityDto> getUserActivityRank(SearchCondition searchCondition) throws BaseException {
		try {
			return ud.selectUserActivitySort(searchCondition);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}
	
	@Override
	public int modifyUserActivity(UserActivityDto userActivityDto) throws BaseException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("key1", "monthly_"+userActivityDto.getKey());
			map.put("key2", "total_"+userActivityDto.getKey());
			map.put("userSeq", userActivityDto.getUserSeq());
			map.put("value", userActivityDto.getValue());
			
			System.out.println(map.get("key1"));
			
			System.out.println(map.get("key2"));
			System.out.println(map.get("userSeq"));
			System.out.println(map.get("value"));

			return ud.updateUserActivity(map);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}



	@Override
	public int getCntByUserNickname(String nickname) throws BaseException {
		try {
			return ud.selectCntByUserNickname(nickname);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int getCntByUserId(String userId) throws BaseException {
		try {
			return ud.selectCntByUserId(userId);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int registerUserBmi(UserBmiDto userBmiDto, int userSeq) throws BaseException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("user_weight", userBmiDto.getUserWeight());
			map.put("user_seq", userSeq);
			return ud.insertUserBmi(map);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int createUserSubTable(UserDto userDto) throws BaseException {
		try {
			return ud.insertUserActivity(userDto);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public CoachDto getUserCoach(int userSeq) throws BaseException {
		try {
			return ud.selectUserCoach(userSeq);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	

}
