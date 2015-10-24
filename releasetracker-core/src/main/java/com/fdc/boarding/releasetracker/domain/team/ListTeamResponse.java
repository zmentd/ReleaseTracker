package com.fdc.boarding.releasetracker.domain.team;

import java.util.List;

public class ListTeamResponse {
	private List<ITeam>				list;
	private Long						count;
	
	public List<ITeam> getList() {
		return list;
	}
	public void setList(List<ITeam> list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
