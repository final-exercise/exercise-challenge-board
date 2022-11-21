package com.ssafy.ssafit.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {
	private String sort = "none";	//정렬 기준
	private String sortDir = "asc";	//정렬 방향
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