package com.fdc.boarding.releasetracker.domain.team;

public class TeamRequest {
	private Long						id;
	private String						name;
	private String						obs;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getObs() {
		return obs;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
}
