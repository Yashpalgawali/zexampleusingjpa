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
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepo emprepo;
	
	@Autowired
	Companyrepo comprepo;
	
	@Autowired
	DesignationRepo desigrepo;
	
	@Autowired
	DepartmentRepo deptrepo;
	
	public String addEmployee(@ModelAttribute("Employee")Employee emp,Model model)
	{
		model.addAttribute("", deptrepo.findAll());
		model.addAttribute("desig", desigserv)
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
		List<Employee>  elist = emprepo.getDeptByEmpid(id);
		
		elist.stream().forEach(e->System.err.println(e.toString()));
		return elist;
	}
	
}