package com.ssafy.ssafit.model.service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Comment.CommentDto;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDetailDto;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

public interface VideoService {
	//비디오 전체 목록 가져오기
	public Page<VideoDto> getVideos(SearchCondition sc) throws BaseException;
	//비디오 하나 가져오기 + 댓글 목록 가져오기 + 뷰카운트 하나 올려줌 + 찜개수가져옴 + 댓글개수 가져옴 + 회원이 찜한 비디온지 가져옴
	public VideoDetailDto getVideo(int videoSeq) throws BaseException;
	//비디오 등록하기
	public int registVideo(VideoDto videoDto) throws BaseException;
	//찜 하기
	public int registUserWish(int videoSeq, int userSeq) throws BaseException;
	//찜 삭제
	public int deleteUserWish(int videoSeq, int userSeq) throws BaseException;
	//리뷰 등록 + 닉네임 가져오기
	public int registComment(CommentDto commentDto) throws BaseException;
	//리뷰 수정
	public int updateComment(CommentDto commentDto) throws BaseException;
	//리뷰 삭제
	public int deleteComment(int commentSeq) throws BaseException;
}
