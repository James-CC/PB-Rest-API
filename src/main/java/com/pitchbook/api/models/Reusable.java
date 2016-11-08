package com.pitchbook.api.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pitchbook.api.dtos.SiteStatusDTO;

@Component
public class Reusable {

	private static final String DELETED_MESSAGE = "Entry with the given value  \"%s\" was deleted";
	private static final String DELETE_ERROR = "No entry with the given value \"%s\" found";
	private static final String ASSOCIATION_DELETE_ERROR = "Entry with the given value \"%s\" has associations which must be deleted first";
	private static final String REPEATED_VALUE = "Entry already exists with given value(s)";
	private static final String MISSING_PARAMS = "Missing required parameters";
	private static final String ALTER_SUCCESS = "Entry altered successfully";
	private static final String MISSING_VALUE = "One or more required parameter was missing";
	private static final String SERVER_ERROR = "Something went wrong!";
	private static final String SUCCESS = "Requested action was successfully completed";
	private static final int[] CODES_LIST = {100, 101, 200, 201, 202, 203, 204, 205, 206, 300, 301, 302, 303, 
			304, 305, 306, 307, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 
			411, 412, 413, 414, 415, 416, 417, 500, 501, 502, 503, 504, 505};
	private Set<String> httpCodes;
	
	public Reusable() {
		initHttpCodes();
	}
	
	public String success() {
		return SUCCESS;
	}
	
	public String serverError() {
		return SERVER_ERROR;
	}
	
	public String deleted(String entity) {
		return String.format(DELETED_MESSAGE, entity);
	}
	
	public String deleteError(String entity) {
		return String.format(DELETE_ERROR, entity);
	}

	public String associationError(String entity) {
		return String.format(ASSOCIATION_DELETE_ERROR, entity);
	}
	
	public String alterSuccess() {
		return ALTER_SUCCESS;
	}
	
	public String missingParams() {
		return MISSING_PARAMS;
	}
	
	public String repeatedValue() {
		return REPEATED_VALUE;
	}
	
	public String incompleteParams() {
		return MISSING_VALUE;
	}
	
	private void initHttpCodes() {
		httpCodes = new HashSet<>();
		for (int i : CODES_LIST) {
			httpCodes.add(String.valueOf(i));
		}
	}
	
	public boolean isValidHttpCode(String code) {
		return httpCodes.contains(code);
	}
	
	public boolean isValidString(String str) {
		if (str == null) {
			return false;
		}
		return str != "";
	}
	
	/*
	 * Helper methods for SiteStatus services
	 */
	/**
	 * Check if the codes passed in are valid http status codes
	 * 
	 * @param siteStatus object containing required parameters
	 * @throws IllegalArgumentException missing codes or invalid codes
	 */
	public void checkParams(SiteStatusDTO siteStatus) throws IllegalArgumentException {
		if (!isValidString(siteStatus.getCode1()) || 
				!isValidString(siteStatus.getCode2())) {
			throw new IllegalArgumentException();
		}
		if (!isValidHttpCode(siteStatus.getCode1()) ||
				!isValidHttpCode(siteStatus.getCode2())) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 
	 * @param saveObj
	 * @param newValues
	 * @param create
	 */
	public void setStatusValues(SiteStatus1 saveObj, SiteStatus2 saveObj2, SiteStatusDTO newValues, boolean create) {
		if (saveObj2 == null) {
			if (create) {
				saveObj.setEntityId(newValues.getEntityId());
			}
			saveObj.setCode1(Integer.parseInt(newValues.getCode1()));
			saveObj.setCode2(Integer.parseInt(newValues.getCode2()));
			int eitherValue = ("200".equals(newValues.getCode1()) || "200".equals(newValues.getCode2())) ? 1 : 0;
			saveObj.setEither(eitherValue);
		} else {
			if (create) {
				saveObj2.setEntityId(newValues.getEntityId());
			}
			saveObj2.setCode1(Integer.parseInt(newValues.getCode1()));
			saveObj2.setCode2(Integer.parseInt(newValues.getCode2()));
			int eitherValue = ("200".equals(newValues.getCode1()) || "200".equals(newValues.getCode2())) ? 1 : 0;
			saveObj2.setEither(eitherValue);
		}
	}
}
