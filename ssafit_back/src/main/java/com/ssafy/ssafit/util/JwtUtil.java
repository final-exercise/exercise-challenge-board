package com.ssafy.ssafit.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.ssafit.config.secret.Secret;
import com.ssafy.ssafit.exception.BaseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	
	/**
	 * 토큰 생성 (유효기간 하루)
	 * @param claimId: 키
	 * @param data: 밸류 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String createToken(String claimId, String data) throws UnsupportedEncodingException {
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim(claimId, data)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+(1000*60*60*24)))
				.signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
				.compact();
	}

	/**
	 * Header에서 ACCESS-TOKEN 으로 JWT 추출
	 * @return String
	 */
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }
    
	/**
	 * 토큰의 유효성 검사
	 * @param token
	 * @throws Exception
	 */
	public void valid(String token) throws BaseException {
		try {
			Jwts.parser().setSigningKey(Secret.JWT_SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (Exception e) {
			throw new BaseException(false, 500, "Jwt Valid Error");
		}
	}

	/**
	 * JWT에서 특정 int 값 추출하기
	 * @param key: 추출할 key
	 * @return
	 * @throws BaseException
	 */
    public int getIntValueFromJwt(String key) throws BaseException{
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0){
            throw new BaseException(false, 500, "Can't read Jwt");
        }

        Jws<Claims> claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(false, 500, "Jwt is NOT Valid");
        }

        return claims.getBody().get(key,Integer.class);
    }

}
