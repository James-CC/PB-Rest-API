package com.pitchbook.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitchbook.api.models.Company;
import com.pitchbook.api.models.exceptions.AssociatedEntitiesExistException;
import com.pitchbook.api.models.exceptions.NoEntityFoundException;
import com.pitchbook.api.repositories.CompanyRepository;
import com.pitchbook.api.repositories.SiteStatus1Repository;
import com.pitchbook.api.repositories.SiteStatus2Repository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private SiteStatus1Repository status1Repo;
	@Autowired
	private SiteStatus2Repository status2Repo;
	
	/* 
	 * Get methods
	 */
	public List<Company> getAll() {
		return companyRepo.findAll();
	}
	
	public Company getByPbId(String pbId) {
		return companyRepo.findByPbId(pbId);
	}
	
	public Company getByEntityId(String entityId) {
		return companyRepo.findByEntityId(entityId);
	}
	
	public Company getByWebUrl(String webUrl) {
		return companyRepo.findByWebUrlIgnoreCase(webUrl);
	}
	
	/*
	 * Delete methods
	 */
	
	/**
	 * Delete company row by given entityId
	 * 
	 * @param entityId String of entityId
	 * @throws NoEntityFoundException no entry with given entityId
	 * @throws AssociatedEntitiesExistException there are siteStatus with this row's entityId
	 */
	public void deleteByEntityId(String entityId) throws NoEntityFoundException, AssociatedEntitiesExistException {
		Long status1Count = status1Repo.countByEntityId(entityId);
		Long status2Count = status2Repo.countByEntityId(entityId);
		if (status1Count > 0 || status2Count > 0) {
			throw new AssociatedEntitiesExistException();
		} else {
			Long count = companyRepo.countByEntityId(entityId);
			if (count > 0) {
				companyRepo.deleteByEntityId(entityId);
			} else {
				throw new NoEntityFoundException();
			}
		}
	}
	
	/**
	 * Delete company row by given pbId
	 * 
	 * @param pbId String of pbId
	 * @throws NoEntityFoundException no entry with given pbId
	 * @throws AssociatedEntitiesExistException there are siteStatus with this row's entityId
	 */
	public void deleteByPbId(String pbId) throws NoEntityFoundException, AssociatedEntitiesExistException {
		Company company = companyRepo.findByPbId(pbId);
		Long status1Count = status1Repo.countByEntityId(company.getEntityId());
		Long status2Count = status2Repo.countByPbId(pbId);
		if (status1Count > 0 || status2Count > 0) {
			throw new AssociatedEntitiesExistException();
		} else {
			Long count = companyRepo.countByPbId(pbId);
			if (count > 0) {
				companyRepo.deleteByPbId(pbId);
			} else {
				throw new NoEntityFoundException();
			}
		}
	}
	
	/**
	 * Delete company row by given url. Looks for direct match
	 * 
	 * @param webUrl String of webUrl
	 * @throws NoEntityFoundException no entry with given url
	 * @throws AssociatedEntitiesExistException there are siteStatus with this row's entityId
	 */
	public void deleteByWebUrl(String webUrl) throws NoEntityFoundException, AssociatedEntitiesExistException {
		Long status1Count = status1Repo.countByUrl1IgnoreCaseContaining(webUrl);
		Long status2Count = status2Repo.countByWebUrlIgnoreCaseContaining(webUrl);
		if (status1Count > 0 || status2Count > 0) {
			throw new AssociatedEntitiesExistException();
		} else {
			Long count = companyRepo.countByWebUrlIgnoreCaseContaining(webUrl);
			if (count > 0) {
				companyRepo.deleteByWebUrlIgnoreCaseContaining(webUrl);
			} else {
				throw new NoEntityFoundException();
			}
		}
	}
	
	/*
	 * Add methods
	 */
	
	/**
	 * Validate given company values and attempt to add to DB.
	 * 
	 * @param newCo new Company object
	 * @return Company object upon save from repo
	 * @throws IllegalArgumentException missing one or more variables
	 * @throws AssociatedEntitiesExistException one or more variable values are not unique
	 */
	public Company addNewCompany(Company newCo) 
			throws IllegalArgumentException, AssociatedEntitiesExistException {
		if (checkValidCompany(newCo)) {
			return companyRepo.save(newCo);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/*
	 * Edit methods
	 */
	
	/**
	 * Check if entry with given entityId exists and edit its values if so
	 * 
	 * @param entityId entityId of entry to edit
	 * @param newCoInfo Company object with info to replace old values
	 * @return Company object with saved values
	 * @throws IllegalArgumentException if entityId is null
	 * @throws NoEntityFoundException if no entry with entityId is found
	 * @throws AssociatedEntitiesExistException 
	 */
	public Company editCompany(String entityId, Company newCoInfo) 
			throws IllegalArgumentException, NoEntityFoundException, AssociatedEntitiesExistException {
		if (entityId == null) {
			throw new IllegalArgumentException();
		}
		Company company = getByEntityId(entityId);
		if (company == null) {
			throw new NoEntityFoundException();
		}
		alterCompany(company, newCoInfo);
		return companyRepo.save(company);
	}
	
	/*
	 * Helper methods
	 */
	
	/**
	 * If new values are not null or empty, change the Company object's values. 
	 * Only input values to edit as parameter
	 * 
	 * @param company entry object to edit
	 * @param newCoInfo values to change the values to
	 * @throws AssociatedEntitiesExistException 
	 */
	private void alterCompany(Company company, Company newCoInfo) throws AssociatedEntitiesExistException {
		checkValueOverlap(newCoInfo, false);
		
		String pbId = newCoInfo.getPbId();
		String companyName = newCoInfo.getCompanyName();
		String webUrl = newCoInfo.getWebUrl();
		
		if (pbId != null && pbId != "") {
			company.setPbId(pbId);
		}
		if (companyName != null && companyName != "") {
			company.setCompanyName(companyName);
		}
		if (webUrl != null && webUrl != "") {
			company.setWebUrl(webUrl);
		}
	}
	
	/**
	 * Check if the company object is valid--entityId, pbId, and companyName are unique in the DB.
	 * 
	 * @param co Company object to check
	 * @return boolean true for no repeated variable value
	 * @throws AssociatedEntitiesExistException one or more of the values are not unique
	 */
	private boolean checkValidCompany(Company co) 
			throws AssociatedEntitiesExistException {
		
		String entityId = co.getEntityId();
		String pbId = co.getPbId();
		String companyName = co.getCompanyName();
		String webUrl = co.getWebUrl();
				
		if (entityId == null || pbId == null || 
				companyName == null || webUrl == null) {
			return false;
		}
		checkValueOverlap(co, true);
		return true;
	}
	
	/**
	 * Check if entries with given values already exist in the DB
	 * 
	 * @param co object to check
	 * @param checkEntityId whether to also check for the entityId (post vs put)
	 * @throws AssociatedEntitiesExistException
	 */
	private void checkValueOverlap(Company co, boolean checkEntityId) throws AssociatedEntitiesExistException {
		int valueOverlap = 0;
		Company checkByPbId = getByPbId(co.getPbId());
		Company checkByWebUrl = getByWebUrl(co.getWebUrl());
		
		if (checkByPbId != null) {
			valueOverlap++;
		}
		if (checkByWebUrl != null) {
			valueOverlap++;
		}
		if (checkEntityId) {
			Company checkByEntityId = getByEntityId(co.getEntityId());
			if (checkByEntityId != null) {
				valueOverlap++;
			}
		}
		if (valueOverlap > 0) {
			throw new AssociatedEntitiesExistException();
		}
	}
}
