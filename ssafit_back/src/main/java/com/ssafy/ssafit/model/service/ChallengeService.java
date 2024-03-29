package com.ssafy.ssafit.model.service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeDto;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeInfoDto;

public interface ChallengeService {
	//챌린지 등록하기
	public int registChallenge(ChallengeDto challengeDto) throws BaseException;
	//참여중인 챌린지 목록 보기 + 회원이 만든 것도 가져오기 
	public Page<ChallengeInfoDto> getJoinedChallenges(int userSeq) throws BaseException;
	//현재 진행중인 챌린지 목록보기
	public Page<ChallengeInfoDto> getValidChallenge() throws BaseException;
	//챌린지 정보 가져오기 + 챌린지의 영상 + 참여중인 회원 + 지금내가참여중인지아닌지
	public ChallengeInfoDto getChallengeInfo(int challengeSeq, int userSeq) throws BaseException;
	//챌린지 참여하기 
	public int joinChallenge(int challengeSeq, int userSeq) throws BaseException;
	//챌린지 daily 참여 완료
	public int doneDailyChallenge(int challengeSeq, int userSeq) throws BaseException;
	//챌린지 등록하면서 비디오 목록 넣기 (반복문)
    public int registChallengeVideo(int challengeSeq, int videoSeq) throws BaseException;
}
