package com.divij.elasticsearch.dto;

import java.util.Map;

public class SearchDetail {
	
	private String userId;
	private String repositoryId;
	private Map<String,String> keyInfo;
	
	SearchDetail(){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public Map<String, String> getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(Map<String, String> keyInfo) {
		this.keyInfo = keyInfo;
	}
	
	

}
