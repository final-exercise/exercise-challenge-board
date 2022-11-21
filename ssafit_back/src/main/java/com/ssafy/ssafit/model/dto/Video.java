package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video {
	private int videoSeq;
	private String videoId;
	private String videoTitle;
	private String videoChannelName;
	private String videoDuration;
	private int videoViewCnt;
	private int videoCal;
}
