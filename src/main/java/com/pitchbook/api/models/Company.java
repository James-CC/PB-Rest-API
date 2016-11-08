package com.pitchbook.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@Column(name = "entity_id")
	private String entityId;
	@Column(name = "pbid")
	private String pbId;
	@Column(name = "co_name")
	private String companyName;
	@Column(name = "weburl")
	private String webUrl;
	
	public Company() {}
	
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
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getWebUrl() {
		return webUrl;
	}
	
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}