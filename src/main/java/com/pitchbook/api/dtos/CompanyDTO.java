package com.pitchbook.api.dtos;

public class CompanyDTO {

	private String entityId;
	private String pbId;
	private String companyName;
	private String webUrl;
	
	public CompanyDTO() {}
	
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getPbId() {
		return pbId;
	}
	public void setPbId(String pbId) {
		this.pbId = pbId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String coName) {
		this.companyName = coName;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}
