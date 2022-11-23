package com.ssafy.ssafit.model.dto.Video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
	private int videoSeq;
	private String videoId;
	private String videoTitle;
	private String videoChannelName;
	private String videoDuration;
	private int videoViewCnt;
	private int videoCal;
	private String videoCreatedAt;
	private String videoUpdatedAt;
	private int videoCommentCnt; // 영상의 댓글 수 
	private int videoWishCnt; // 영상의 찜 수
	private String superType; // 대분류 카테고리
	private String subType; // 소분류 운동부위
}
