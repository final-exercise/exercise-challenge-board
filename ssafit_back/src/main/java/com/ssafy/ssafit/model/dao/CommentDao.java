package com.ssafy.ssafit.model.dao;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dto.Comment.CommentDto;

public interface CommentDao {
	//비디오 seq에 따른 댓글 목록 select
	public Page<CommentDto> getCommentsByVideoSeq(int videoSeq);
	//댓글 seq에 따른 대댓글 목록 select
	public Page<CommentDto> getCommentsByCommentSeq(int commentSeq);
	//리뷰 등록
	public int insertComment(CommentDto commentDto);
	//리뷰 수정
	public int updateComment(CommentDto commentDto);
	//리뷰 삭제
	public int deleteComment(int commentSeq);
	//닉네임 가져오기 <"Table", "Column", "SeqColumn", "Seq">
	public String selectNickname(Map<String, String> map);
}
