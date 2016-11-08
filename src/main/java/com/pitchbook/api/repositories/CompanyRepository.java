package com.pitchbook.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pitchbook.api.models.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {

	List<Company> findAll();
	
	Company findByEntityId(String entityId);
	
	Company findByPbId(String pbId);
	
	Company findByWebUrlIgnoreCase(String webUrl);
	
	Long countByEntityId(String entityId);
	
	void deleteByEntityId(String entityId);
	
	Long countByPbId(String pbId);
	
	void deleteByPbId(String pbId);
	
	Long countByWebUrlIgnoreCaseContaining(String url);
	
	void deleteByWebUrlIgnoreCaseContaining(String url);
	
	Company save(Company company);
}
