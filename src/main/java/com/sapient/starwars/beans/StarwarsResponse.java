package com.sapient.starwars.beans;

import java.util.ArrayList;
import java.util.List;

public class StarwarsResponse {
	
	private String type;		//from request
	private String name;		//from request
	private int count;
	private String errorMessage;
	private List<Object> results = new ArrayList<Object>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Object> getResults() {
		return results;
	}

	public void setResults(List<Object> results) {
		this.results = results;
	}

	

	
}
