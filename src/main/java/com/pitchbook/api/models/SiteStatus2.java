package com.pitchbook.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site_status_2")
public class SiteStatus2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "pbid")
	private String pbId; 
	@Column(name = "entity_id")
	private String entityId;
	@Column(name = "co_name")
	private String companyName;
	@Column(name = "web_url")
	private String webUrl;
	@Column(name = "url_1")
	private String url1;
	@Column(name = "code_1")
	private int code1;
	@Column(name = "url_2")
	private String url2;
	@Column(name = "code_2")
	private int code2;
	@Column(name = "either")
	private int either;
	
	public SiteStatus2() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPbId() {
		return pbId;
	}
	
	public void setPbId(String pbId) {
		this.pbId = pbId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
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
	
	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public int getCode1() {
		return code1;
	}

	public void setCode1(int code1) {
		this.code1 = code1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public int getCode2() {
		return code2;
	}

	public void setCode2(int code2) {
		this.code2 = code2;
	}

	public int getEither() {
		return either;
	}

	public void setEither(int either) {
		this.either = either;
	}
	
	/*@Override
	public String toString() {
		return String.format(
				"SiteStatus2[id=%d, entityId='%s', url1='%s', code1=%d, url2='%s', code2=%d, either=%d]",
				id, entityId, url1, code1, url2, code2, either);
	}*/
}
