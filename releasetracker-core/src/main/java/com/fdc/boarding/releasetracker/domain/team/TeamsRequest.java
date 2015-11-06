package com.fdc.boarding.releasetracker.domain.team;

import com.fdc.boarding.releasetracker.domain.AbstractRequest;


public class TeamsRequest extends AbstractRequest {
	private Integer						page;
	private Integer						countPerPage;
	private String						orderBy;
	private boolean						isAsc;

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(Integer countPerPage) {
		this.countPerPage = countPerPage;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return isAsc;
	}
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
}
