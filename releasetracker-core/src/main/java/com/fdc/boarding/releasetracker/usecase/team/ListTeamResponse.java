package com.fdc.boarding.releasetracker.usecase.team;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;

public class ListTeamResponse extends AbstractResponse{
	private static final long 			serialVersionUID 	= 1L;
	
	private List<TeamResponse>			list;
	private Long						count;
	
	public ListTeamResponse(){
		super();
	}
	
	public List<TeamResponse> getList() {
		return list;
	}
	public void setList(List<TeamResponse> list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
