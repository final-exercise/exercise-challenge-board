package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.model.dto.Video.VideoDto;

public interface ChallengeDao {
	//챌린지 생성 (삽입)
	public int insertChallenge(ChallengeDto challengeDto);
	//내가 참여중인 챌린지 가져오기
	public Page<ChallengeDto> selectJoinedChallenges(int userSeq);
	//내가 만든 챌린지 가져오기
	public ChallengeDto selectMyChallenge(int userSeq);
	//현재 진행중인 챌린지 가져오기 -> isvalid=1 인거 가져오면되나?
	public Page<ChallengeDto> selectValidChallenge();
	//챌린지seq의 챌린지 가져오기
	public ChallengeDto selectChallenge(int challengeSeq);
	//챌린지seq의 영상 목록 가져오기
	public List<VideoDto> selectChallengeVideos(int challengeSeq);
	//챌린지seq에 참여하고 있는 회원 목록 가져오기
	public List<UserDto> selectJoinedUsers(int challengeSeq);
	//챌린지 join하기  <"challengeSeq", "userSeq">
	public int insertChallengeJoin(Map<String, Integer> map);
	//챌린지 join한 상태에서 그날 참여진행(운동함)
	public int insertChallengeJoinDaily(int challengeJoinSeq);
	//회원seq가 해당 챌린지seq의 joinSeq 가져오기 <"challengeSeq", "userSeq">
	//public ChallengeJoinDto selectChallengeJoinDto(Map<String, Integer> map); 어떤거 해야하지
	public int selectChallengeJoinSeq(Map<String, Integer> map);
	//회원seq가 해당 챌린지seq에 참여중인지 아닌지 <"challengeSeq", "userSeq">
	public int selectUserJoinChallenge(Map<String, Integer> map);
	 //챌린지 생성 후 비디오 삽입 <"challenge_seq" , "video_seq">
    public int insertChallengeVideo(Map<String, Integer> map);
	
}
