package com.pitchbook.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitchbook.api.dtos.SiteStatusDTO;
import com.pitchbook.api.models.Company;
import com.pitchbook.api.models.Reusable;
import com.pitchbook.api.models.SiteStatus2;
import com.pitchbook.api.models.exceptions.NoEntityFoundException;
import com.pitchbook.api.repositories.CompanyRepository;
import com.pitchbook.api.repositories.SiteStatus2Repository;

@Service
public class SiteStatus2Service {

	@Autowired
	private SiteStatus2Repository siteStatusRepo;
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private Reusable reusable;
	
	public SiteStatus2Service() {}
	
	/* 
	 * Get methods
	 */
	public List<SiteStatus2> getAll() {
		return siteStatusRepo.findAll();
	}
	
	public SiteStatus2 getById(int id) {
		return siteStatusRepo.findById(id);
	}
	
	public List<SiteStatus2> getByPbId(String pbId) {
		return siteStatusRepo.findByPbId(pbId);
	}
	
	public List<SiteStatus2> getByEntityId(String entityId) {
		return siteStatusRepo.findByEntityId(entityId);
	}
	
	public List<SiteStatus2> getByCompanyName(String companyName) {
		return siteStatusRepo.findByCompanyName(companyName);
	}
	
	public List<SiteStatus2> getByWebUrl(String webUrl) {
		return siteStatusRepo.findByWebUrlIgnoreCaseContaining(webUrl);
	}
	
	public List<SiteStatus2> getByEither(int either) {
		return siteStatusRepo.findByEither(either);
	}
	
	/*
	 * Delete methods
	 */
	public void deleteById(int id) throws NoEntityFoundException {
		Long count = siteStatusRepo.countById(id);
		if (count > 0) {
			siteStatusRepo.deleteById(id);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	public void deleteByPbId(String pbId) throws NoEntityFoundException {
		Long count = siteStatusRepo.countByPbId(pbId);
		if (count > 0) {
			siteStatusRepo.deleteByPbId(pbId);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	public void deleteByEntityId(String entityId) throws NoEntityFoundException {
		Long count = siteStatusRepo.countByEntityId(entityId);
		if (count > 0) {
			siteStatusRepo.deleteByEntityId(entityId);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	public void deleteByCompanyName(String companyName) throws NoEntityFoundException {
		Long count = siteStatusRepo.countByCompanyName(companyName);
		if (count > 0) {
			siteStatusRepo.deleteByCompanyName(companyName);
		} else {
			throw new NoEntityFoundException();
		}
	}
	
	public void deleteByWebUrl(String webUrl) throws NoEntityFoundException {
		Long count = siteStatusRepo.countByWebUrlIgnoreCaseContaining(webUrl);
		if (count > 0) {
			siteStatusRepo.deleteByWebUrlIgnoreCaseContaining(webUrl);
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
	public SiteStatus2 addNewStatus(SiteStatusDTO siteStatus) throws NoEntityFoundException, IllegalArgumentException {
		Company co = companyRepo.findByEntityId(siteStatus.getEntityId());
		if (co == null) {
			throw new NoEntityFoundException();
		}
		reusable.checkParams(siteStatus);
		SiteStatus2 status = new SiteStatus2();
		String strippedUrl = (co.getWebUrl()).replaceFirst("www.", "");
		status.setUrl1("http://" + strippedUrl);
		status.setUrl2("http://www" + strippedUrl);
		status.setPbId(co.getPbId());
		status.setCompanyName(co.getCompanyName());
		status.setWebUrl(strippedUrl);
		reusable.setStatusValues(null, status, siteStatus, true);
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
	public SiteStatus2 editStatus(int id, SiteStatusDTO siteStatus) throws NoEntityFoundException {
		if (id == 0) {
			throw new NoEntityFoundException();
		}
		reusable.checkParams(siteStatus);
		SiteStatus2 current = getById(id);
		if (current == null) {
			throw new NoEntityFoundException();
		}
		reusable.setStatusValues(null, current, siteStatus, false);
		return siteStatusRepo.save(current);
	}
}
