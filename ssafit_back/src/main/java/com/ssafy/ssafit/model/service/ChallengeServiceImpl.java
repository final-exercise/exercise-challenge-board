package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dao.ChallengeDao;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeDto;
import com.ssafy.ssafit.model.dto.Challenge.ChallengeInfoDto;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	
	private final boolean FAIL = false;
	private final boolean SUCCESS = true;
	
	@Autowired
	private ChallengeDao chd;
	
	@Override
	public int registChallenge(ChallengeDto challengeDto) throws BaseException {
		try {
			return chd.insertChallenge(challengeDto);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public Page<ChallengeDto> getJoinedChallenges(int userSeq) throws BaseException {
		try {
			return chd.selectJoinedChallenges(userSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public Page<ChallengeDto> getValidChallenge() throws BaseException {
		try {
			return chd.selectValidChallenge();
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public ChallengeInfoDto getChallengeInfo(int challengeSeq, int userSeq) throws BaseException {
		try {
			ChallengeInfoDto cid = new ChallengeInfoDto();
			cid.setChallengeDto(chd.selectChallenge(challengeSeq));
			cid.setChallengeUsers(chd.selectJoinedUsers(challengeSeq));
			cid.setChallengeVideos(chd.selectChallengeVideos(challengeSeq));
			return cid;
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int joinChallenge(int challengeSeq, int userSeq) throws BaseException {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("challengeSeq", challengeSeq);
			map.put("userSeq", userSeq);
			return chd.insertChallengeJoin(map);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int doneDailyChallenge(int challengeSeq, int userSeq) throws BaseException {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("challengeSeq", challengeSeq);
			map.put("userSeq", userSeq);
			int challengeJoinSeq = chd.selectChallengeJoinSeq(map);
			return chd.insertChallengeJoinDaily(challengeJoinSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}
	
	  @Override
	    public int registChallengeVideo(int challengeSeq, int videoSeq) throws BaseException {
	        try {
	            Map<String, Integer> map = new HashMap<>();
	            map.put("challengeSeq", challengeSeq);
	            map.put("videoSeq", videoSeq);
	            return chd.insertChallengeVideo(map);
	        } catch(Exception e) {
	        	e.printStackTrace();
	            throw new BaseException(FAIL, 500, "database error");
	        }
	    }

}
