package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Comment.CommentDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDto;
import com.ssafy.ssafit.model.service.VideoService;
import com.ssafy.ssafit.model.service.VideoServiceImpl;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
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
	
	// 1) [GET] /video?page={page}&superType={superType}&subType={subType}&key={key}&word={word}&sort={sort}&sortDir={sortDir}
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getVideos(SearchCondition sc) {

		System.out.println(sc.toString());
		if(sc.getKey().length()==0 && sc.getKey() !="none") {
			sc.setKey("none");
		} // 이건 할필요가 없나?
		if(sc.getSort().length()==0 && sc.getSort() !="none") {
			sc.setSort("none");
		}
		if(sc.getSortDir().length() == 0) {
			sc.setSortDir("desc");
		}
		
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {
			
			int page = sc.getPage();
			if(page == 0)
				page = 1;
			
			if(sc.getLimit() != 0) {
                PageHelper.startPage(page, sc.getLimit());
            }
            else {
                PageHelper.startPage(page, 10);
            }

			Page<VideoDto> res = vs.getVideos(sc);
			
			result.put("res", res);
			result.put("total", res.getTotal());
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
			int memberSeq;
			int isCoach;
			
			if(((String)jwtUtil.getValueFromJwt("authority")).equals("user")) {
				memberSeq = Integer.parseInt((String) jwtUtil.getValueFromJwt("userSeq"));
				isCoach = 0;
			} else {
				memberSeq = Integer.parseInt((String) jwtUtil.getValueFromJwt("coachSeq"));
				isCoach = 1;
			}
			
			
			result.put("res", vs.getVideo(videoSeq, memberSeq, isCoach));
//			System.out.println(result.get("res"));
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
	
	// 3) [POST] /video/{videoSeq}?bundleId={bundleId}
	@PostMapping("/{videoSeq}")
	public ResponseEntity<Map<String, Object>> registComment(@PathVariable("videoSeq") int videoSeq, CommentDto commentDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {
			int userSeq = 0;
			int coachSeq = 0;
			if(jwtUtil.getValueFromJwt("userSeq")!=null) {
				commentDto.setUserSeq(Integer.parseInt((String) jwtUtil.getValueFromJwt("userSeq")));
			} else {
				commentDto.setCoachSeq(Integer.parseInt((String) jwtUtil.getValueFromJwt("coachSeq")));
			}
	
			int res = ((Integer) vs.registComment(commentDto)).intValue();
			
			if (res != 1) {
				throw new BaseException(FAIL, 500, "insert comment fail");
			}
			
			//댓글 depth 설정해줘야함
			if(commentDto.getBundleId() == 0) {
				commentDto.setBundleId(commentDto.getCommentSeq());
				commentDto.setCommentDepth(0);
			}
			else {
				commentDto.setBundleId(commentDto.getBundleId());
				commentDto.setCommentDepth(1);
			}

			//depth설정해준거 반영
			res = vs.updateComment(commentDto);
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
			
			if (res == 0) {
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
	
	// 5) [POST] /video/comment/{commentSeq}
	@PostMapping("/comment/{commentSeq}")
	public ResponseEntity<Map<String, Object>> updateComment(@PathVariable("commentSeq") int commentSeq, CommentDto commentDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int res = vs.updateComment(commentDto);
			
			if (res == 0) {
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
		System.out.println(videoSeq);
		try {
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());

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
			int userSeq = Integer.parseInt(jwtUtil.getValueFromJwt("userSeq").toString());

			int res = vs.deleteUserWish(videoSeq, userSeq);
			
			if (res == 0) {
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
	
	// 8) [POST] /video //비디오 등록 코치만
	@PostMapping() 
	public ResponseEntity<Map<String, Object>> registVideo(VideoDto videoDto) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int res = vs.registVideo(videoDto); // insert 된 row 개수
			
			if (res == 0) { 
				throw new BaseException(FAIL, 500, "insert video fail");
			}
			
			result.put("res", res);
			result.put("message", "insert video success");
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
	
	//
	@GetMapping("/{videoSeq}/mylist")
	public ResponseEntity<Map<String, Object>> getIsWish(@PathVariable("videoSeq") int videoSeq) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {

			int userSeq = Integer.parseInt((String) jwtUtil.getValueFromJwt("userSeq"));
			int res = vs.selectUserWish(userSeq, videoSeq);

			result.put("res", res);
			result.put("message", "getIsWish success");
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
