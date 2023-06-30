package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepo;

@Service("empserv")
public class EmployeeServImpl implements EmployeeService {

	@Autowired
	EmployeeRepo emprepo;
	

	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	public LocalDateTime today = LocalDateTime.now();  
	
	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		Employee empl = emprepo.save(emp); 
		if(empl!=null)
		{
			return empl;
		}
		else {
			return empl;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		
		List<Employee> elist = emprepo.getAllEmployees();
		
		elist.stream().forEach(e->System.err.println(e.getDepartment().toString()));
		return elist;
	}

	@Override
	public Employee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		
		Long eid = Long.parseLong(id);
		return emprepo.findById(eid).get() ;
	}

	@Override
	public int updateEmployee(Employee empl) {
		// TODO Auto-generated method stub
		int res = emprepo.updateEmployee(empl.getEmp_name(), empl.getEmp_email(), empl.getEmp_status(), empl.getDepartment().getDept_id(), empl.getDesignation().getDesig_id(), empl.getEmp_id());
		if(res>0)
		{
			return res ;
		}
		else {
			
		}	return res ;
	
	}

	@Override
	public  List<Employee>  getDeptByEmpId(String id) {
		// TODO Auto-generated method stub
		Long eid = Long.valueOf(id);
			   
		return emprepo.getDeptByEmpId(eid);
	}

}
