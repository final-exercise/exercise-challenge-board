package com.ssafy.ssafit.model.dto.Comment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	private int commentSeq;
	private String commentContent;
	private int videoSeq;
	private int coachSeq;
	private int userSeq;
	private int commentDepth;
	private int bundleId;
	private String commentCreatedAt;
	private String commentUpdatedAt;
	private String nickname; // resultMap collection
	private boolean isCoach;
	private List<CommentDto> replys;

}
