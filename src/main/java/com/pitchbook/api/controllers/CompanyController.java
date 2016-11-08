package com.pitchbook.api.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitchbook.api.models.Company;
import com.pitchbook.api.models.Reusable;
import com.pitchbook.api.models.exceptions.AssociatedEntitiesExistException;
import com.pitchbook.api.models.exceptions.NoEntityFoundException;
import com.pitchbook.api.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private Reusable reusable;
	
	@RequestMapping(value = "/entityid/{entityId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteByEntityId(@PathVariable("entityId") String entityId) {
		try {
			Company status = companyService.getByEntityId(entityId);
			if (status == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/pbid/{pbId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteByPbId(@PathVariable("pbId") String pbId) {
		try {
			Company status = companyService.getByPbId(pbId);
			if (status == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/weburl/{webUrl}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteByUrl(@PathVariable("webUrl") String webUrl) {
		try {
			Company status = companyService.getByWebUrl(webUrl);
			if (status == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Company> getSiteStatus() {
		List<Company> status;
		try {
			return this.companyService.getAll();
		} catch (Exception ex) {
			status = null;
		}
		return status;
	}
	
	@RequestMapping(value = "/entityid/{entityId}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByEntityId(@PathVariable("entityId") String entityId) {
		try {
			companyService.deleteByEntityId(entityId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(entityId));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(entityId));
		} catch (AssociatedEntitiesExistException ae) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(reusable.associationError(entityId));
		}
	}
	
	@RequestMapping(value = "/pbid/{pbId}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByPbId(@PathVariable("pbId") String pbId) {
		try {
			companyService.deleteByPbId(pbId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(pbId));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(pbId));
		} catch (AssociatedEntitiesExistException ae) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(reusable.associationError(pbId));
		}
	}
	
	@RequestMapping(value = "/weburl/{webUrl}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByUrl(@PathVariable("webUrl") String webUrl) {
		try {
			companyService.deleteByWebUrl(webUrl);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(webUrl));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(webUrl));
		} catch (AssociatedEntitiesExistException ae) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(reusable.associationError(webUrl));
		}
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> addNew(@RequestBody Company co) {
		try {
			Company saved = companyService.addNewCompany(co);
			if (saved == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reusable.serverError());
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(reusable.success());
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(reusable.missingParams());
		} catch (AssociatedEntitiesExistException ae) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(reusable.repeatedValue());
		}	
	}
	
	@RequestMapping(value = "/edit/{entityId}", method = RequestMethod.PUT)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> alter(@PathVariable("entityId") String entityId, @RequestBody Company newCoInfo) {
		try {
			companyService.editCompany(entityId, newCoInfo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(reusable.missingParams());
		} catch (NoEntityFoundException ne) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(entityId));
		} catch (AssociatedEntitiesExistException ae) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(reusable.repeatedValue());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.alterSuccess());
	}
	
}
