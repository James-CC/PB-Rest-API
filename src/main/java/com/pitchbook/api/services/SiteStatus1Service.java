package com.pitchbook.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitchbook.api.dtos.SiteStatusDTO;
import com.pitchbook.api.models.Company;
import com.pitchbook.api.models.Reusable;
import com.pitchbook.api.models.SiteStatus1;
import com.pitchbook.api.models.exceptions.NoEntityFoundException;
import com.pitchbook.api.repositories.CompanyRepository;
import com.pitchbook.api.repositories.SiteStatus1Repository;

@Service
@Transactional
public class SiteStatus1Service {

	@Autowired
	private SiteStatus1Repository siteStatusRepo;
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private Reusable reusable;
	
	public SiteStatus1Service() {}
	
	/* 
	 * Get methods
	 */
	public List<SiteStatus1> getAll() {
		return siteStatusRepo.findAll();
	}
	
	public SiteStatus1 getById(int id) {
		return siteStatusRepo.findById(id);
	}
	
	public List<SiteStatus1> getByEntityId(String entityId) {
		return siteStatusRepo.findByEntityId(entityId);
	}
	
	public List<SiteStatus1> getByUrl(String url) {
		return siteStatusRepo.findByUrl1IgnoreCaseContaining(url);
	}
	
	public List<SiteStatus1> getByEither(int either) {
		return siteStatusRepo.findByEither(either);
	}
	
	// Custom search by pb_id
	public List<SiteStatus1> getByPbId(String pbId) {
		Company company = companyRepo.findByPbId(pbId);
		if (company == null) {
			return null;
		}
		return siteStatusRepo.findByEntityId(company.getEntityId());
	}
	
	/*
	 * Delete methods
	 */
	/**
	 * Delete siteStatus1 row by given id
	 * 
	 * @param id int of id
	 * @throws NoEntityFoundException no entry with given id
	 */
	public void deleteById(int id) throws NoEntityFoundException {
		Long count = siteStatusRepo.countById(id);
		if (count > 0) {
			siteStatusRepo.deleteById(id);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	/**
	 * Delete siteStatus1 rows by given entityId
	 * 
	 * @param entityId String of entityId
	 * @throws NoEntityFoundException no entries with given entityId
	 */
	public void deleteByEntityId(String entityId) throws NoEntityFoundException {
		Long count = siteStatusRepo.countByEntityId(entityId);
		if (count > 0) {
			siteStatusRepo.deleteByEntityId(entityId);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	/**
	 * Delete siteStatus1 rows by given url
	 * 
	 * @param url String of url
	 * @throws NoEntityFoundException no entries with given url
	 */
	public void deleteByUrl(String url) throws NoEntityFoundException {
		Long count = siteStatusRepo.countByUrl1IgnoreCaseContaining(url);
		if (count > 0) {
			siteStatusRepo.deleteByUrl1IgnoreCaseContaining(url);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	/*
	 * Add methods
	 */
	
	/**
	 * Insert SiteStatus1 by checking parameters and attempting to add.
	 * 
	 * @param siteStatus DTO of required parameters
	 * @return SiteStatus1 object upon insert attempt
	 * @throws NoEntityFoundException no EntityId exists in the Company table
	 * @throws IllegalArgumentException missing one or more parameters
	 */
	public SiteStatus1 addNewStatus(SiteStatusDTO siteStatus) throws NoEntityFoundException, IllegalArgumentException {
		Company co = companyRepo.findByEntityId(siteStatus.getEntityId());
		if (co == null) {
			throw new NoEntityFoundException();
		}
		reusable.checkParams(siteStatus);
		SiteStatus1 status = new SiteStatus1();
		String strippedUrl = (co.getWebUrl()).replaceFirst("www.", "");
		status.setUrl1("http://www" + strippedUrl);
		status.setUrl2("http://" + strippedUrl);
		reusable.setStatusValues(status, null, siteStatus, true);
		return siteStatusRepo.save(status);
	}
	
	/*
	 * Edit methods
	 */
	/**
	 * Edit status given id of the entry and code1 and code2 variables
	 * 
	 * @param id int of id
	 * @param siteStatus DTO of required parameters
	 * @return SiteStatus1 upon save
	 * @throws NoEntityFoundException no entry with given id found
	 */
	public SiteStatus1 editStatus(int id, SiteStatusDTO siteStatus) throws NoEntityFoundException {
		if (id == 0) {
			throw new NoEntityFoundException();
		}
		reusable.checkParams(siteStatus);
		SiteStatus1 current = getById(id);
		if (current == null) {
			throw new NoEntityFoundException();
		}
		reusable.setStatusValues(current, null, siteStatus, false);
		return siteStatusRepo.save(current);
	}
}
