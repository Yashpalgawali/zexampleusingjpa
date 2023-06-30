package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Company;
import com.example.demo.repository.Companyrepo;

@Service("compserv")
public class CompanyServImpl implements CompanyService {

	@Autowired
	Companyrepo comprepo;
	
	@Override
	public Company saveCompany(Company comp) {
		// TODO Auto-generated method stub
		return comprepo.save(comp);
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return comprepo.findAll();
	}

	@Override
	public Company getCompanyById(Long cid) {
		// TODO Auto-generated method stub
		try {
			Company comp = comprepo.findById(cid).get();
			return comp;
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public int updateCompany(Company comp) {
		// TODO Auto-generated method stub
		return comprepo.updateCompany(comp.getComp_id(), comp.getComp_name());
	}

}
