package com.ssafy.ssafit.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/*
 * jwt 추가 필요라고 쓴 애들은 모두 인자에서 userSeq 빼고
 * jwt에서 추출해서 사용하도록 수정 필요
 */

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Coach.CoachDto;
import com.ssafy.ssafit.model.dto.User.UserActivityDto;
import com.ssafy.ssafit.model.dto.User.UserBmiDto;
import com.ssafy.ssafit.model.dto.User.UserDietDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.User.UserWorkoutDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDto;
import com.ssafy.ssafit.model.service.CoachService;
import com.ssafy.ssafit.model.service.CoachServiceImpl;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.model.service.UserServiceImpl;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;

@RequestMapping("/user")
@RestController
@Api("User")
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8080", "*" })
public class UserController {

	private JwtUtil jwtUtil;
	private UserService us;
	private CoachService cs;

	private final boolean SUCCESS = true;
	private final boolean FAIL = false;

	@Autowired
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Autowired
	public void setUserService(UserServiceImpl us) {
		this.us = us;
	}
	
	@Autowired
	public void setCoachService(CoachServiceImpl cs) {
		this.cs = cs;
	}

	// 1) [POST] /user/signup
	@PostMapping("signup")
	public ResponseEntity<Map<String, Object>> signup(UserDto userDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int res = us.signup(userDto);
		
			int newUserSeq = userDto.getUserSeq();

			
			us.createUserSubTable(userDto);
			
			String userType = userDto.getUserType();
			cs.matchCoach(newUserSeq,userType);
			
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "signup insert fail");
			}

			result.put("message", "signup success");
			result.put("res", res);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 2) [POST] /user/login
	@PostMapping("login")
	public ResponseEntity<Map<String, Object>> login(UserDto userDto) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			UserDto resUser = us.login(userDto);

			if (resUser == null) {
				result.put("isSuccess", FAIL);
				result.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<Map<String, Object>>(result, status);
			}
			// jwt발급
			Map<String, String> map = new HashMap<>();
			map.put("userSeq", String.valueOf(resUser.getUserSeq()));
			map.put("authority", "user");
			
			result.put("access-token", jwtUtil.createToken(map));
			result.put("message", "로그인 성공!");
			result.put("res", resUser);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);

		} catch (Exception exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 3) [GET] /user
	// jwt 추가 필요
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> getUser() {
		int userSeq;
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			UserDto resUser = us.getUser(userSeq);

			result.put("message", "get user success");
			result.put("res", resUser);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);

		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 4) [DELETE] /user
	// jwt 추가 필요
	@DeleteMapping("")
	public ResponseEntity<Map<String, Object>> deleteUser() {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			int res = us.deleteUser(userSeq);

			if (res != 1) {
				throw new BaseException(FAIL, 500, "delete user error");
			}

			result.put("isSuccess", SUCCESS);
			result.put("message", "user delete success");
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}

	}

	// 5) [POST] /user
	@PostMapping("")
	public ResponseEntity<Map<String, Object>> postUser(UserDto userDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int res = us.modifyUser(userDto);
			if (res != 1) {
				throw new BaseException(FAIL, 500, "user modify failed");
			}
			result.put("isSuccess", SUCCESS);
			result.put("message", "user modify success");
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 6) [GET] /user/bmi
	@GetMapping("/bmi")
	public ResponseEntity<Map<String, Object>> getUserBmi() {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			List<UserBmiDto> resBMIs = us.getUserBmi(userSeq);

			result.put("message", "get userBmi success");
			result.put("res", resBMIs);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 7) [GET] /user/workout?month={month}&year={year}
	@GetMapping("/workout")
	public ResponseEntity<Map<String, Object>> getUserMonthlyWorkout(@RequestParam(required=false) Integer year, 
																	@RequestParam(required=false) Integer month) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (month == null) {
				month = LocalDate.now().getMonthValue();
			}
			if(year == null) {
				year = LocalDate.now().getYear();
			}

			int userSeq = Integer.parseInt((String)jwtUtil.getValueFromJwt("userSeq"));
			List<UserWorkoutDto> resWorkouts = us.getUserMonthlyWorkout(userSeq, month, year);

			result.put("message", "get userMonthlyWorkout success");
			result.put("res", resWorkouts);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 8) [POST] /user/workout
	// dto 추후 수정 필요 ->캘린더 api 보고
	// jwt 추가 필요 
	@PostMapping("/workout")
	public ResponseEntity<Map<String, Object>> postUserWorkout(UserWorkoutDto userWorkoutDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt((String)jwtUtil.getValueFromJwt("userSeq"));
			userWorkoutDto.setUserSeq(userSeq);
			int res = us.registUserWorkout(userWorkoutDto);

			if (res != 1) {
				throw new BaseException(FAIL, 500, "post userWorkout failed");
			}
			result.put("message", "post userWorkout success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 9) [GET] /user/diet?month={month}&year={year}
	@GetMapping("/diet")
	public ResponseEntity<Map<String, Object>> getUserMonthlyDiet(@RequestParam(required=false) Integer year, 
																	@RequestParam(required=false) Integer month) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (month == null) {
				month = LocalDate.now().getMonthValue();
			}
			if(year == null) {
				year = LocalDate.now().getYear();
			}

			int userSeq = Integer.parseInt((String)jwtUtil.getValueFromJwt("userSeq"));

			List<UserDietDto> resDiets = us.getUserMonthlyDiet(userSeq, month, year);

			result.put("message", "get userMonthlyDiet success");
			result.put("res", resDiets);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 10) [POST] /user/diet
	// dto 추후 수정 필요 ->캘린더 api 보고
	// jwt 추가 필요 
	@PostMapping("/diet")
	public ResponseEntity<Map<String, Object>> postUserDiet(UserDietDto userDietDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int userSeq = Integer.parseInt((String)jwtUtil.getValueFromJwt("userSeq"));

			userDietDto.setUserSeq(userSeq);
			System.out.println(userSeq);
			int res = us.registUserDiet(userDietDto);

			if (res != 1) {
				throw new BaseException(FAIL, 500, "post userDiet failed");
			}
			result.put("message", "post userDiet success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 11) [GET] /user/wish?page={page}
	// jwt 추가 필요 
	@GetMapping("/wish")
	public ResponseEntity<Map<String, Object>> getUserMyList(@RequestParam(required = false) Integer page) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (page == null) {
				page = 1;
			}
			
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			PageHelper.startPage(page, 10);
//			int userSeq = jwtUtil.getIntValueFromJwt("userSeq");
			
			Page<VideoDto> res = us.getWish(userSeq);
			
			result.put("total", res.getTotal());
			result.put("message", "get userWish success");
			result.put("res", res);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 12) [GET] /diet?name={name}
	// 나중에

	// 13) [GET] /user/activity?sort={sort}
	@GetMapping("/activity")
	public ResponseEntity<Map<String, Object>> getUserActivity(SearchCondition searchCondition) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {
			//상위 5명
			PageHelper.startPage(1, 5);
			//sortDir는 무조건 내림차순
			searchCondition.setSortDir("DESC");
			
			//sort key 검사
			String key = searchCondition.getKey();
			
			if(!(key.equals("monthly_attendance") || key.equals("monthly_reply") || key.equals("monthly_exp"))){
				throw new BaseException(FAIL, 500, "unvalid key");
			}
	
			
			Page<UserActivityDto> res = us.getUserActivityRank(searchCondition);

			result.put("message", "get userRank success");
			result.put("res", res);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (Exception exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 14) [POST] /user/activity
	// jwt 수정 필요 
	@PostMapping("/activity")
	public ResponseEntity<Map<String, Object>> postUserActivity(UserActivityDto userActivityDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			
			int userSeq = Integer.parseInt((String)jwtUtil.getValueFromJwt("userSeq"));
			userActivityDto.setUserSeq(userSeq);
			
			String key = userActivityDto.getKey();
			
			if (!key.equals("replay") && !key.equals("exp")) {
				throw new BaseException(FAIL, 500, "unvalid key");
			}

			int res = us.modifyUserActivity(userActivityDto);
			if (res != 1) {
				throw new BaseException(FAIL, 500, "post userActivity fail");
			}

			result.put("message", "post userActivity success");
			result.put("res", res);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 38) [GET] /user/nickname?value={nickname}
	@GetMapping("/nickname")
	public ResponseEntity<Map<String, Object>> isDuplicatedNickname(@RequestParam String value) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int res = 1;
			if (value != null && value.trim().length() > 0) {
				res = us.getCntByUserNickname(value);
			}

			if (res > 0) {
				result.put("res", true);
			} else {
				result.put("res", false);
			}

			result.put("message", "get isDuplicatedNickname success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 39) [GET] /user/userId?value={userId}
	@GetMapping("/userId")
	public ResponseEntity<Map<String, Object>> isDuplicatedUserId(@RequestParam String value) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {

			int res = 1;
			if (value != null && value.trim().length() > 0) {
				res = us.getCntByUserId(value);
			}

			if (res > 0) {
				result.put("res", true);
			} else {
				result.put("res", false);
			}

			result.put("message", "get isDuplicatedNickname success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 40) [POST] /user/bmi
	// 추후 jwt 추가 필요 및 dto 수정 필요
	@PostMapping("/bmi")
	public ResponseEntity<Map<String, Object>> postUserBmi(int userWeight) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			
			UserBmiDto userBmiDto = new UserBmiDto();
			userBmiDto.setUserWeight(userWeight);
			
			if(userBmiDto.getUserWeight()<1) {
				throw new BaseException(FAIL, 500, "unvalid value(weight)");
			}
			
			if(us.registerUserBmi(userBmiDto, userSeq)!=1) {
				throw new BaseException(FAIL, 500, "post userBmi fail");
			}

			result.put("message", "post userBmi success");
			result.put("res", 1);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}
	
	// 41) [GET] /user/coach
	@GetMapping("/coach")
	public ResponseEntity<Map<String, Object>> getUserCoach() {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			
			CoachDto res = us.getUserCoach(userSeq);
			

			result.put("message", "get userCoach success");
			result.put("res", res);
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException exception) {
			result.put("isSuccess", FAIL);
			result.put("message", exception.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}
	
}
