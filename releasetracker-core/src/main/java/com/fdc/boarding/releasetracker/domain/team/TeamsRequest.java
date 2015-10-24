package com.fdc.boarding.releasetracker.domain.team;

import com.fdc.boarding.releasetracker.domain.team.ITeamPersistenceGateway.OrderBy;

public class TeamsRequest {
	private Integer						page;
	private Integer						countPerPage;
	private OrderBy						orderBy;
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
	public OrderBy getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return isAsc;
	}
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
}
