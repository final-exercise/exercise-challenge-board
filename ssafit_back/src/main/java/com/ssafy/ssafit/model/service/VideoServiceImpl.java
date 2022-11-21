package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Comment.CommentDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDetailDto;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

@Service
public class VideoServiceImpl implements VideoService {
	
	private final boolean FAIL = false;
	private final boolean SUCCESS = true;
	
	@Autowired
	private VideoDao vd;
	@Autowired
	private CommentDao cd;
	
	@Override
	public Page<VideoDto> getVideos(SearchCondition sc) throws BaseException {
		try {
			return vd.selectVideos(sc);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public VideoDetailDto getVideo(int videoSeq) throws BaseException {
		//비디오 하나 가져오기 + 댓글 목록 가져오기 + 뷰카운트 하나 올려줌 + 찜개수가져옴 + 댓글개수 가져옴 + 회원이 찜한 비디온지 가져옴
		try {
			return vd.selectVideo(videoSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int registVideo(VideoDto videoDto) throws BaseException {
		try {
			return vd.insertVideo(videoDto);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int registUserWish(int videoSeq, int userSeq) throws BaseException {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("videoSeq", videoSeq);
			map.put("userSeq", userSeq);
			return vd.insertUserWish(map);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int deleteUserWish(int videoSeq, int userSeq) throws BaseException {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("videoSeq", videoSeq);
			map.put("userSeq", userSeq);
			return vd.deleteUserWish(map);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int registComment(CommentDto commentDto) throws BaseException {
		//리뷰 등록 + 닉네임 가져오기
		try {
			return cd.insertComment(commentDto);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int updateComment(CommentDto commentDto) throws BaseException {
		try {
			return cd.updateComment(commentDto);
		}catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int deleteComment(int commentSeq) throws BaseException {
		try {
			return cd.deleteComment(commentSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

}