package com.pitchbook.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pitchbook.api.models.SiteStatus2;

@Repository
@Transactional
public interface SiteStatus2Repository extends CrudRepository<SiteStatus2, Integer> {

	List<SiteStatus2> findAll();

	SiteStatus2 findById(int id);
	
	List<SiteStatus2> findByPbId(String pbId);

	List<SiteStatus2> findByEntityId(String entityId);

	List<SiteStatus2> findByCompanyName(String companyName);
	
	List<SiteStatus2> findByWebUrlIgnoreCaseContaining(String url);

	List<SiteStatus2> findByEither(int either);
	
	Long countById(int id);
	
	void deleteById(int id);
	
	Long countByPbId(String pbId);
	
	void deleteByPbId(String pbId);
	
	Long countByEntityId(String entityId);
	
	void deleteByEntityId(String entityId);
	
	Long countByCompanyName(String companyName);
	
	void deleteByCompanyName(String companyName);
	
	Long countByWebUrlIgnoreCaseContaining(String url);
	
	void deleteByWebUrlIgnoreCaseContaining(String url);
	
	SiteStatus2 save(SiteStatus2 siteStatus);
}
