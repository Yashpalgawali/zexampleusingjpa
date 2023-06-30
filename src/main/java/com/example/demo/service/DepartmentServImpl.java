package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Department;
import com.example.demo.repository.DepartmentRepo;

@Service("deptserv")
public class DepartmentServImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepo deptrepo;
	
	@Override
	public Department saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.save(dept);
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return deptrepo.getAllDepartments();
	}

	@Override
	public Department getDepartmentById(Long depid) {
		// TODO Auto-generated method stub
		try {
			Department dept = deptrepo.findById(depid).get();
			return dept;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public int updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.updateDepartment(dept.getDept_id(), dept.getDept_name(), dept.getCompany().getComp_id());
	}

	@Override
	public List<Department> getDepartmentByCompid(Long id) {
		// TODO Auto-generated method stub
		return deptrepo.getDepartmentByCompid(id);
	}

}
