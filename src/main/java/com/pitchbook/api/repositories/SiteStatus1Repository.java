package com.pitchbook.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pitchbook.api.models.SiteStatus1;

@Repository
@Transactional
public interface SiteStatus1Repository extends CrudRepository<SiteStatus1, Integer> {

	List<SiteStatus1> findAll();

	SiteStatus1 findById(int id);
	
	List<SiteStatus1> findByEntityId(String entityId);
	
	List<SiteStatus1> findByUrl1IgnoreCaseContaining(String url);
	
	List<SiteStatus1> findByEither(int either);
	
	Long countById(int id);
	
	void deleteById(int id);
	
	Long countByEntityId(String entityId);
	
	void deleteByEntityId(String entityId);
	
	Long countByUrl1IgnoreCaseContaining(String url);
	
	void deleteByUrl1IgnoreCaseContaining(String url);
	
	SiteStatus1 save(SiteStatus1 siteStatus);
}