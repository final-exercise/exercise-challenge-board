package com.ssafy.ssafit.model.dto.Challenge;

import java.util.List;

import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeInfoDto {
	private ChallengeDto challengeDto;
	private List<UserDto> challengeUsers; // 챌린지에 참여중인 회원 정보
	private List<VideoDto> challengeVideos; // 챌린지에 포함된 영상 정보
}
