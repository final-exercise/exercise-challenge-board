package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.model.dao.ChallengeDao;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeDto;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeInfoDto;

@Service
public class ChallengeServiceImpl implements ChallegeService {
	
	private final boolean FAIL = false;
	private final boolean SUCCESS = true;
	
	@Autowired
	private ChallengeDao chd;
	
	@Override
	public int registChallenge(ChallengeDto challengeDto) {
		
		return 0;
	}

	@Override
	public Page<ChallengeDto> getJoinedChallenges(int userSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ChallengeDto> getValidChallenge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChallengeInfoDto getChallengeInfo(int challengeSeq, int userSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int joinChallenge(int challengeSeq, int userSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doneDailyChallenge(int challengeSeq, int userSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
