package com.pitchbook.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site_status")
public class SiteStatus1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "entity_id")
	private String entityId;
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
	
	public SiteStatus1() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
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
				"SiteStatus1[id=%d, entityId='%s', url1='%s', code1=%d, url2='%s', code2=%d, either=%d]",
				id, entityId, url1, code1, url2, code2, either);
	}*/
}
