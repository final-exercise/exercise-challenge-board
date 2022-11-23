package com.ssafy.ssafit.model.dto.Video;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDetailDto {
	private VideoDto videodto;
	private List<CommentDto> comments; // 댓글 목록
	private int videoIsWish; // 로그인 회원이 좋아요 했는지
}
