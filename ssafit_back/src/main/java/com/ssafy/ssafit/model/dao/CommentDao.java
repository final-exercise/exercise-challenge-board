package com.ssafy.ssafit.model.dao;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dto.Comment.CommentDto;

public interface CommentDao {
	//비디오 seq에 따른 댓글 목록 select
	public Page<CommentDto> getComments(int videoSeq);
	//비디오 댓글 개수
	public int selectCommentCnt(int videoSeq);
	//리뷰 등록
	public int insertComment(CommentDto commentDto);
	//리뷰 수정
	public int updateComment(CommentDto commentDto);
	//리뷰 삭제
	public int deleteComment(int commentSeq);
	//닉네임 가져오기 <"Table", "Column", "SeqColumn", "Seq">
	public String selectNickname(Map<String, String> map);
}
