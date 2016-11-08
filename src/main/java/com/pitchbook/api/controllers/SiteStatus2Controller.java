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

import com.pitchbook.api.dtos.SiteStatusDTO;
import com.pitchbook.api.models.Reusable;
import com.pitchbook.api.models.SiteStatus2;
import com.pitchbook.api.models.exceptions.NoEntityFoundException;
import com.pitchbook.api.services.SiteStatus2Service;

@RestController
@RequestMapping("/sitestatus2")
public class SiteStatus2Controller {

	@Autowired
	private SiteStatus2Service statusService;
	@Autowired
	private Reusable reusable;
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteById(@PathVariable("id") Integer id) {
		try {
			SiteStatus2 status = statusService.getById(id);
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
			List<SiteStatus2> status = statusService.getByPbId(pbId);
			if (status == null || status.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/entityid/{entityId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteByEntityId(@PathVariable("entityId") String entityId) {
		try {
			List<SiteStatus2> status = statusService.getByEntityId(entityId);
			if (status == null || status.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@RequestMapping(value = "/companyname/{companyName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteByCompanyName(@PathVariable("companyName") String companyName) {
		try {
			List<SiteStatus2> status = statusService.getByCompanyName(companyName);
			if (status == null || status.isEmpty()) {
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
			List<SiteStatus2> status = statusService.getByWebUrl(webUrl);
			if (status == null || status.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/either/{either}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSiteByEither(@PathVariable("either") Integer either) {
		try {
			List<SiteStatus2> status = statusService.getByEither(either);
			if (status == null || status.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<SiteStatus2> getSiteStatus() {
		List<SiteStatus2> status;
		try {
			return this.statusService.getAll();
		} catch (Exception ex) {
			status = null;
		}
		return status;
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		try {
			statusService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(id.toString()));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(id.toString()));
		}
	}
	
	@RequestMapping(value = "/pbid/{pbId}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByPbId(@PathVariable("pbId") String pbId) {
		try {
			statusService.deleteByPbId(pbId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(pbId));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(pbId));
		}
	}
	
	@RequestMapping(value = "/entityid/{entityId}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByEntityId(@PathVariable("entityId") String entityId) {
		try {
			statusService.deleteByEntityId(entityId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(entityId));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(entityId));
		}
	}
	
	@RequestMapping(value = "/companyname/{companyName}", method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByCompanyName(@PathVariable("companyName") String companyName) {
		try {
			statusService.deleteByCompanyName(companyName);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(companyName));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(companyName));
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/weburl/{webUrl}")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> deleteByUrl(@PathVariable("webUrl") String webUrl) {
		try {
			statusService.deleteByWebUrl(webUrl);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.deleted(webUrl));
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(webUrl));
		}
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> addNewStatus(@RequestBody SiteStatusDTO siteStatus) {
		try {
			SiteStatus2 status = statusService.addNewStatus(siteStatus);
			if (status == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reusable.serverError());
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.success());
			}
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(siteStatus.getEntityId()));
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@Transactional
	public ResponseEntity<?> editStatus(@PathVariable("id") Integer id, @RequestBody SiteStatusDTO siteStatus) {
		try {
			SiteStatus2 status = statusService.editStatus(id, siteStatus);
			if (status == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reusable.serverError()); 
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(reusable.success());
			}
		} catch (NoEntityFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reusable.deleteError(siteStatus.getEntityId()));
		}
	}
}