package com.ssafy.ssafit.model.dto.Challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {
    private int challengeSeq;
    private int userSeq;
    private boolean isPublic;
    private int duration; // 이거계산 어떻게 하지?
    private String endDate;
    private String challengeCreatedAt;
    private String challengeUpdatedAt;
   
}

