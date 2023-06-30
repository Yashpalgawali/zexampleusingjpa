package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Department;

public interface DepartmentService {

	public Department saveDepartment(Department dept);
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentById(Long depid);
	
	public int updateDepartment(Department dept);
	
	public List<Department> getDepartmentByCompid(Long id);
	
}
