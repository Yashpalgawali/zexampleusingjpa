package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Company;

@Repository("comprepo")
public interface Companyrepo extends JpaRepository<Company, Long> {

	
	@Transactional
	@Modifying
	@Query("UPDATE Company c SET c.comp_name=?2 WHERE c.comp_id=?1")
	public int updateCompany(Long cid,String cname);
}
