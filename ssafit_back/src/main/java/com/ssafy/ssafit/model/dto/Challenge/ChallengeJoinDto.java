package com.ssafy.ssafit.model.dto.Challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//이거안씀
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeJoinDto {
	private int challengeJoinSeq;
	private int challengeSeq; 
	private int userSeq; // private UserDto userDto; 로 해야하나?
	private int totalJoinCnt; //조인으로 구해도 될 것 같은데
	// 챌린지 참여 확인 테이블은 insert만 하는 거라서 굳이 dto 필요없고 dao로 insert만들고 service에서 돌려주는 걸로 ?
}
