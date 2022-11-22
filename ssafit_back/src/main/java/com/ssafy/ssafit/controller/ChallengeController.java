package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeDto;
import com.ssafy.ssafit.model.service.ChallengeService;
import com.ssafy.ssafit.model.service.ChallengeServiceImpl;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;

@RequestMapping("/challenge")
@RestController
@Api("Challenge")
public class ChallengeController {
	private JwtUtil jwtUtil;
	private ChallengeService chs;

	private final boolean SUCCESS = true;
	private final boolean FAIL = false;

	@Autowired
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Autowired
	public void setChallengeService(ChallengeServiceImpl chs) {
		this.chs = chs;
	}

	// 1) [POST] /challenge
	@PostMapping()
	public ResponseEntity<Map<String, Object>> registChallenge(ChallengeDto challengeDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			challengeDto.setCreateUserSeq(userSeq);
			int res = chs.registChallenge(challengeDto);
			
			if(res != 1) {
				throw new BaseException(FAIL, 500, "insert new challenge fail");
			}
			
			result.put("res", res);
			result.put("message", "insert new challenge success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 2) [GET] /challenge/{userSeq}?page={page}
	@GetMapping("/{userSeq}")
	public ResponseEntity<Map<String, Object>> getJoinedChallenges(@PathVariable("userSeq") int userSeq, int page) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (page == 0)
				page = 1;
			PageHelper.startPage(page, 10);

			result.put("res", chs.getJoinedChallenges(userSeq));
			result.put("message", "get joinedChallenges success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 3) [GET] /challenge?page={page}
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getValidChallenge(int page) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			if (page == 0)
				page = 1;
			PageHelper.startPage(page, 10);

			result.put("res", chs.getValidChallenge());
			result.put("message", "get validChallenges success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}

	// 4) [GET] /challenge/{challengeSeq}
	@GetMapping("/{challengeSeq}")
	public ResponseEntity<Map<String, Object>> getChallengeInfo(@PathVariable("challengeSeq") int challengeSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			result.put("res", chs.getChallengeInfo(challengeSeq, userSeq));
			result.put("message", "get challenge info success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}
	
	// 5) [POST] /challenge/{challengeSeq}
	@PostMapping("/{challengeSeq}")
	public ResponseEntity<Map<String, Object>> joinChallenge(@PathVariable("challengeSeq") int challengeSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			int res = chs.joinChallenge(challengeSeq, userSeq);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "insert challenge join fail");
			}
			
			result.put("res", res);
			result.put("message", "insert challenge join success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}
	
	// 6) [POST] /challenge/{challengeSeq}/done
	@PostMapping("/{challenge}/done")
	public ResponseEntity<Map<String, Object>> doneDailyChallenge(@PathVariable("challengeSeq") int challengeSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());
			int res = chs.doneDailyChallenge(challengeSeq, userSeq);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "insert challenge daily fail");
			}
			
			result.put("res", res);
			result.put("message", "insert challenge daily success");
			result.put("isSuccess", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(result, status);
		} catch (BaseException e) {
			result.put("isSuccess", FAIL);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}
	}
	

}