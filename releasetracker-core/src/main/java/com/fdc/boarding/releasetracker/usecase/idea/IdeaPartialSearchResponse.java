package com.fdc.boarding.releasetracker.usecase.idea;

import java.io.Serializable;

public class IdeaPartialSearchResponse implements Serializable{
	private static final long 			serialVersionUID = 1L;
	
	private Long 						id;
	private String						ideaNumber;
	private String						prjNumber;
	private String						name;
	
	public IdeaPartialSearchResponse(){
		super();
	}

	public Long getId() {
		return id;
	}

	public String getIdeaNumber() {
		return ideaNumber;
	}

	public String getName() {
		return name;
	}

	public String getPrjNumber() {
		return prjNumber;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdeaNumber(String ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrjNumber(String prjNumber) {
		this.prjNumber = prjNumber;
	}
}
