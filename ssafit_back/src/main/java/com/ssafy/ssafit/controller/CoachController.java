package com.ssafy.ssafit.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.Coach.CoachDto;
import com.ssafy.ssafit.model.dto.User.UserBmiDto;
import com.ssafy.ssafit.model.dto.User.UserDietDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.User.UserWorkoutDto;
import com.ssafy.ssafit.model.service.CoachService;
import com.ssafy.ssafit.model.service.CoachServiceImpl;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.model.service.UserServiceImpl;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;

@RequestMapping("/coach")
@RestController
@Api("Coach")
public class CoachController {

	private JwtUtil jwtUtil;
	private CoachService cs;
	private UserService us;

	private final boolean SUCCESS = true;
	private final boolean FAIL = false;

	@Autowired
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Autowired
	public void setCoachService(CoachServiceImpl cs) {
		this.cs = cs;
	}

	@Autowired
	public void setUserService(UserServiceImpl us) {
		this.us = us;
	}

	// 26) [POST] /coach/signup
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signup(CoachDto coachDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int res = cs.signup(coachDto);

			if (res != 1) {
				throw new BaseException(FAIL, 500, "signup coach fail");
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

	// 27) [POST] /coach/login
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(CoachDto coachDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			CoachDto resCoach = cs.login(coachDto);

			if (resCoach == null) {
				result.put("isSuccess", FAIL);
				result.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<Map<String, Object>>(result, status);
			}
			// jwt발급
			Map<String, String> map = new HashMap<>();
			map.put("coachSeq", String.valueOf(resCoach.getCoachSeq()));
			map.put("authority", "coach");

			result.put("access-token", jwtUtil.createToken(map));
			result.put("message", "로그인 성공!");
			result.put("res", resCoach);
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

	// 28) [GET] /coach
	// jwt 추가 필요
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getCoach(int coachSeq) {
//			int userSeq;
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
//				userSeq = jwtUtil.getIntValueFromJwt("userSeq");
			CoachDto resCoach = cs.getCoach(coachSeq);

			result.put("message", "get coach success");
			result.put("res", resCoach);
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

	// 29) [DELETE] /coach
	// jwt 추가 필요
	@DeleteMapping("/")
	public ResponseEntity<Map<String, Object>> deleteCoach(int coachSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
//				int userSeq = jwtUtil.getIntValueFromJwt("userSeq");
			int res = cs.deleteCoach(coachSeq);

			if (res != 1) {
				throw new BaseException(FAIL, 500, "delete coach error");
			}

			result.put("isSuccess", SUCCESS);
			result.put("message", "coach delete success");
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}

	}

	// 30) [POST] /coach
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> postCoach(CoachDto coachDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int res = cs.modifyCoach(coachDto);
			if (res != 1) {
				throw new BaseException(FAIL, 500, "coach modify failed");
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

	// 31) [GET] /coach/manage
	// jwt ㅊ추가 필요
	@GetMapping("/manage")
	public ResponseEntity<Map<String, Object>> getManageUser(@RequestParam(required = false) Integer page,
			int coachSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (page == null) {
				page = 1;
			}

			PageHelper.startPage(page, 10);
//				int userSeq = jwtUtil.getIntValueFromJwt("userSeq");
			Page<UserDto> res = cs.getManageUser(coachSeq);

			result.put("message", "get manageUsers success");
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

//	32	GET	/coach/:userSeq/bmi
	// 32) [GET] /coach/:userSeq/bmi
	@GetMapping("/{userSeq}/bmi")
	public ResponseEntity<Map<String, Object>> getManageUserBmi(@PathVariable int userSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {

//						int userSeq = jwtUtil.getIntValueFromJwt("userSeq");
			List<UserBmiDto> resBMIs = us.getUserBmi(userSeq);

			result.put("message", "get manageUserBmi success");
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

//	33	GET	/coach/:userSeq/workout?month={month}
	// [GET] /coach/:userSeq/workout?month={month}&year={year}
	@GetMapping("/{userSeq}/workout")
	public ResponseEntity<Map<String, Object>> getManageUserMonthlyWorkout(@RequestParam(required = false) Integer year,
			@RequestParam(required = false) Integer month, int userSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (month == null) {
				month = LocalDate.now().getMonthValue();
			}
			if (year == null) {
				year = LocalDate.now().getYear();
			}

			// int userSeq = jwtUtil.getIntValueFromJwt("userSeq");
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

//	34	GET	/coach/:userSeq/diet?month={month}
	@GetMapping("{userSeq}/diet")
	public ResponseEntity<Map<String, Object>> getUserMonthlyDiet(@RequestParam(required = false) Integer year,
			@RequestParam(required = false) Integer month, int userSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (month == null) {
				month = LocalDate.now().getMonthValue();
			}
			if (year == null) {
				year = LocalDate.now().getYear();
			}

//int userSeq = jwtUtil.getIntValueFromJwt("userSeq");
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
}
