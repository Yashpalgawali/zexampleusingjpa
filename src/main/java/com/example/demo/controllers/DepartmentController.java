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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Company;
import com.example.demo.models.Department;
import com.example.demo.repository.Companyrepo;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentRepo deptrepo;
	
	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	CompanyService compserv;
	
	@GetMapping("adddepartment")
	public String addDepartment(Model model)
	{	
		model.addAttribute("clist", compserv.getAllCompanies());
		return "AddDepartment" ;
	}
	
	
	@PostMapping("savedepartment")
	public String saveDept(@ModelAttribute("Department") Department dept,RedirectAttributes attr)
	{
		Department depart =  deptserv.saveDepartment(dept);
		if(depart!=null) {
			attr.addFlashAttribute("response", "Department is saved successfully");
			return "redirect:/viewdepartments";
		}
		else
		{
			attr.addFlashAttribute("reserr", "Department is not saved ");
			return "redirect:/viewdepartments";
		}
	}
	
	
	@GetMapping("viewdepartments")
	public String getAllDepts(Model model)
	{	
		model.addAttribute("clist", compserv.getAllCompanies());
		return "ViewDepartments" ;
	}
	
	@GetMapping("/")
	public String homePage()
	{	
		return "Home" ;
	}
	
	@GetMapping("getdeptbycompid/{id}")@ResponseBody
	public List<Department> getDepartmentByCompId(@PathVariable("id") Long id)
	{
		return deptserv.getDepartmentByCompid(id);
	}
	
	
	@GetMapping("editdeptbyid/{id}")
	public String getDeptById(@PathVariable("id")Long id,Model model,RedirectAttributes attr) 
	{
		Department dept = deptserv.getDepartmentById(id);
		if(dept!=null)
		{
			model.addAttribute("clist", compserv.getAllCompanies());
			model.addAttribute("dept", dept);
			return "EditDepartment";
		}
		else {
			attr.addFlashAttribute("reserr", "No department found for given Id");
			return "redirect:/viewdepartments";
		}
		
	}
	
	@PostMapping("updatedepartment")
	public String updateDepartment(@ModelAttribute("Department") Department dept,RedirectAttributes attr)
	{
		int res = deptserv.updateDepartment(dept);
		if(res>0)
		{
			attr.addFlashAttribute("response", "Department is updated successfully");
			return "redirect:/viewdepartments";
		}
		else {
			attr.addFlashAttribute("reserr", " Department is not updated ");
			return "redirect:/viewdepartments";
		}
	}
	
//	@GetMapping("viewdepartments")@ResponseBody
//	public List<Department> getAllDepts()
//	{
//		List<Department> dlist = deptrepo.findAll();
//		dlist.stream().forEach(e->System.err.println(e.toString()));
//		return dlist ;
//	}
}
