package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Comment.CommentDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.service.VideoService;
import com.ssafy.ssafit.model.service.VideoServiceImpl;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;

@RequestMapping("/video")
@RestController
@Api("Video")
public class VideoController {
	private JwtUtil jwtUtil;
	private VideoService vs;
	
	private final boolean SUCCESS = true;
	private final boolean FAIL = false;

	@Autowired
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Autowired
	public void setVideoService(VideoServiceImpl vs) {
		this.vs = vs;
	}
	
	// 1) [GET] /video/page={page}&super={super}&sub={sub}&key={key}&word={word}&sort={sort}&sortDir={sortDir}
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getVideos(SearchCondition sc) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int page = sc.getPage();
			if(page == 0)
				page = 1;
			
			PageHelper.startPage(page, 10);
			result.put("res", vs.getVideos(sc));
			result.put("message", "get videos success");
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
	
	// 2) [GET] /video/{videoSeq}
	@GetMapping("/{videoSeq}")
	public ResponseEntity<Map<String, Object>> getVideo(@PathVariable("videoSeq") int videoSeq, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Map<String, String> tokenMap = (HashMap) jwtUtil.getValueFromJwt("access-token");
			int userSeq = Integer.parseInt(tokenMap.get("userSeq"));
			result.put("res", vs.getVideo(videoSeq, userSeq));
			//videoDetail 말고 result.put("comment", 댓글목록가져오기로 할까);
			result.put("message", "get video success");
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
	
	// 3) [POST] /video/{videoSeq}
	@PostMapping("/{videoSeq}")
	public ResponseEntity<Map<String, Object>> registComment(@PathVariable("videoSeq") int videoSeq, CommentDto commentDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int res = vs.registComment(commentDto);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "insert comment fail");
			}
			
			result.put("res", res);
			result.put("message", "regist comment success");
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
	
	// 4) [DELETE] /video/{commentSeq}
	@DeleteMapping("/{commentSeq}")
	public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("commentSeq") int commentSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int res = vs.deleteComment(commentSeq);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "delete comment fail");
			}
			
			result.put("res", res);
			result.put("message", "delete comment success");
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
	
	// 5) [POST] /video/{commentSeq}
	@PostMapping("/{commentSeq}")
	public ResponseEntity<Map<String, Object>> updateComment(@PathVariable("commentSeq") int commentSeq, CommentDto commentDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int res = vs.updateComment(commentDto);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "update comment fail");
			}
			
			result.put("res", res);
			result.put("message", "update comment success");
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
	
	// 6) [POST] /video/mylist/{videoSeq}
	@PostMapping("/mylist/{videoSeq}")
	public ResponseEntity<Map<String, Object>> registUserWish(@PathVariable("videoSeq") int videoSeq, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Map<String, String> tokenMap = (HashMap) jwtUtil.getValueFromJwt("access-token");
			int userSeq = Integer.parseInt(tokenMap.get("userSeq"));
			
			int res = vs.registUserWish(videoSeq, userSeq);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "insert mylist fail");
			}
			
			result.put("res", res);
			result.put("message", "regist mylist success");
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
	
	// 7) [DELETE] /video/mylist/{videoSeq}
	@DeleteMapping("/mylist/{videoSeq}")
	public ResponseEntity<Map<String, Object>> deleteUserWish(@PathVariable("videoSeq") int videoSeq, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Map<String, String> tokenMap = (HashMap) jwtUtil.getValueFromJwt("access-token");
			int userSeq = Integer.parseInt(tokenMap.get("userSeq"));
			
			int res = vs.deleteUserWish(videoSeq, userSeq);
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "delete mylist fail");
			}
			
			result.put("res", res);
			result.put("message", "delete mylist success");
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
