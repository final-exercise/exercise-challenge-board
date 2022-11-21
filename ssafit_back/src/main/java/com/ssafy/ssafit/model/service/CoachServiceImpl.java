package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssafy.ssafit.config.secret.Secret;
import com.ssafy.ssafit.exception.BaseException;
import com.ssafy.ssafit.model.dao.CoachDao;
import com.ssafy.ssafit.model.dto.Coach.CoachDto;
import com.ssafy.ssafit.model.dto.User.UserDto;
import com.ssafy.ssafit.util.AES128;

@Service
public class CoachServiceImpl implements CoachService {

	private final boolean FAIL = false;
	private final boolean SUCCESS = true;

	@Autowired
	private CoachDao cd;
	
	@Override
	public int signup(CoachDto coachDto) throws BaseException {
		String pwd;

		try {
			// password 암호화
			pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(coachDto.getCoachPassword());
			coachDto.setCoachPassword(pwd);
		} catch (Exception e) {
			throw new BaseException(false, 500, "password encrypt error");
		}

		try {
			return cd.insertCoach(coachDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(false, 500, "database error");
		}
	}

	@Override
	public CoachDto login(CoachDto coachDto) throws BaseException {
		try {
			String reqId = coachDto.getCoachId();
			CoachDto resCoach = cd.selectCoachByCoachId(reqId);
			try {
				if(resCoach==null 
						|| resCoach.getCoachId().length()==0 
						|| !new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(resCoach.getCoachPassword()).equals(coachDto.getCoachPassword())) {
					return null;
				} else {
					
					return resCoach;
				}
			} catch (Exception e) {
				throw new BaseException(FAIL, 500, "decrypt error");
			}
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error" );
		}
	}

	@Override
	public CoachDto getCoach(int coachSeq) throws BaseException {
		try {
			return cd.selectCoach(coachSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int deleteCoach(int cocachSeq) throws BaseException {
		try {
			return cd.deleteCoach(cocachSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public int modifyCoach(CoachDto coachDto) throws BaseException {
		try {
			return cd.updateCoach(coachDto);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

	@Override
	public Page<UserDto> getManageUser(int coachSeq) throws BaseException {
		try {
			return cd.selectManageUser(coachSeq);
		} catch(Exception e) {
			throw new BaseException(FAIL, 500, "database error");
		}
	}

}
