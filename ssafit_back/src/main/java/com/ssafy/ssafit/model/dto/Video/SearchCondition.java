package com.ssafy.ssafit.model.dto.Video;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {
	private String key = "none"; // 검색어 기준
	private String word; // 검색어
	private String sort = "none";	//정렬 기준
	private String sortDir = "asc";	//정렬 방향
	private String superType = "none";	//대분류 카테고리
	private String subType = "none";	//소분류 운동부위
	
	
	private int page;
	
	//lombok error
	public int getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}
	public void setPage(int page) {
		this.page=page;
	}

}