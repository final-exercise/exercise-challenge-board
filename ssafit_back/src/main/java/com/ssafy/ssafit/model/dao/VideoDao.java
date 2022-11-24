package com.ssafy.ssafit.model.dao;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dto.Video.SearchCondition;
import com.ssafy.ssafit.model.dto.Video.VideoDetailDto;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

public interface VideoDao {
	// 비디오 전체 목록 select / sc에 따른 비디오 목록 select
	public Page<VideoDto> selectVideos(SearchCondition sc);

	// 비디오 seq에 따른 비디오 하나 select
	public VideoDto selectVideo(int videoSeq);

	// 비디오 등록하기 (코치만 가능)
	public int insertVideo(VideoDto videoDto);
	
	// 운동 super type seq 가져오기
	public String selectVideoSuperTypeSeq(String superType);
	
	//  운동 super type seq 가져오기	
	public String selectVideoSubTypeSeq(String subType);

	// 운동 super type 등록 <videoSeq, superTypeSeq>
	public int insertSuperType(Map<String, String> map);

	// 운동 sub type 등록 <videoSeq, subTypeSeq>
	public int insertSubType(Map<String, String> map);

	// 뷰카운트 +1
	public int updateViewCntOne(int videoSeq);

	// 회원이 찜한 비디오 인지 <videoSeq, userSeq>
	public int selectUserWish(Map<String, Integer> map);

	// 찜 하기 <videoSeq, userSeq>
	public int insertUserWish(Map<String, Integer> map);

	// 찜 삭제 <videoSeq, userSeq>
	public int deleteUserWish(Map<String, Integer> map);
	
	//댓글 수 증가
	public int updateUserComment(int userSeq);
}
