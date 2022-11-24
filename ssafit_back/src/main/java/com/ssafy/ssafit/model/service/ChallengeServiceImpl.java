package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;
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
	public Page<ChallengeInfoDto> getJoinedChallenges(int userSeq) throws BaseException {
		System.out.println("서비스");
		try {
			Page<ChallengeInfoDto> cid = new Page<>();
			System.out.println(userSeq);//여기까지 됨
			List<ChallengeDto> challenges = chd.selectJoinedChallenges(userSeq);
			System.out.println(challenges.size());
			
			for(ChallengeDto cd : challenges) {
				System.out.println(cd.getChallengeSeq());
				ChallengeInfoDto temp = new ChallengeInfoDto();
				temp.setChallengeDto(cd);
				//영상정보 리스트가져오기
				temp.setChallengeVideos(chd.selectChallengeVideos(cd.getChallengeSeq()));
				System.out.println("영상정보가져옴");
				//참여회원 리스트가져오기
				temp.setChallengeUsers(chd.selectJoinedUsers(cd.getChallengeSeq()));
				cid.add(temp);
			}
			
			return cid;
		} catch(Exception e) {
			e.printStackTrace();
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public Page<ChallengeInfoDto> getValidChallenge() throws BaseException {
		try {
			Page<ChallengeInfoDto> cid = new Page<>();
			List<ChallengeDto> challenges = chd.selectValidChallenge();
			for(ChallengeDto cd : challenges) {
				ChallengeInfoDto temp = new ChallengeInfoDto();
				temp.setChallengeDto(cd);
				//영상정보 리스트가져오기
				temp.setChallengeVideos(chd.selectChallengeVideos(cd.getChallengeSeq()));
				//참여회원 리스트가져오기
				temp.setChallengeUsers(chd.selectJoinedUsers(cd.getChallengeSeq()));
				cid.add(temp);
			}
			
			return cid;
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
