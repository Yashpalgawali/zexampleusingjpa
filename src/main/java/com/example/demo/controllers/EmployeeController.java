package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Employee;
import com.example.demo.repository.Companyrepo;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.DesignationRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DesignationService;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepo emprepo;
	
	@Autowired
	EmployeeService empserv;
	
	@Autowired
	Companyrepo comprepo;
	
	@Autowired
	DesignationService desigserv;
	
	@Autowired
	CompanyService compserv;
	
	@Autowired
	DepartmentService deptserv;
	
	@GetMapping("addemployee")
	public String addEmployee(@ModelAttribute("Employee")Employee emp,Model model)
	{
		model.addAttribute("clist", compserv.getAllCompanies());
		model.addAttribute("dlist", desigserv.getAllDesignations());
		return "AddEmployee";
	}
	
	@PostMapping("saveemp")
	public String saveEmployee(@RequestBody Employee emp)
	{
		if(emprepo.save(emp)!=null)
		{
			return "redirect:/viewemp";
		}
		else
			return "emp not saved";
	}
	
	@GetMapping("viewemp")
	public String getAllEmployees(Model model)
	{
		model.addAttribute("elist",emprepo.findAll());
		return "ViewEmployees";
		//return emprepo.getAllEmployees();
	}
	
	@GetMapping("getdeptbyempid/{id}")@ResponseBody
	public List<Employee> getDepartmentbyEmpId(@PathVariable("id") Long id)
	{
		//List<Employee>  elist = emprepo.getDeptByEmpId(id);
		
		List<Employee>  elist = empserv.getDeptByEmpId(""+id);
		//elist.stream().forEach(e->System.err.println(e.toString()));
		return elist;
	}
	
}