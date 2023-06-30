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
import org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Company;
import com.example.demo.repository.Companyrepo;
import com.example.demo.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	Companyrepo comprepo;
	
	@Autowired
	CompanyService compserv;
	
	@PostMapping("savecompany")
	public String saveCOmpany(@RequestBody Company comp)
	{
		Company cmp = compserv.saveCompany(comp);
		if(cmp!=null){
			return "redirect:/viewcompanies";
		}
		else {
			return "Not saved";
		}
	}
	
	@GetMapping("/viewcompanies")
	private String getAllCompanies(Model model)
	{
		model.addAttribute("clist", compserv.getAllCompanies());
		return "ViewCompany";
	}
	@GetMapping("editcompbyid/{id}")
	private String editCompById(@PathVariable("id") Long id,Model model,RedirectAttributes attr)
	{
		Company comp = compserv.getCompanyById(id);
		if(comp!=null)
		{
			model.addAttribute("comp", comp);
			return "EditCompany";
		}
		else
		{
			attr.addFlashAttribute("reserr", "No company found for given ID");
			return "redirect:/viewcompanies";
		}
	}
	
	
	
	@PostMapping("updatecompany")
	public String updateCompany(@ModelAttribute("Company")Company comp,RedirectAttributes attr)
	{
		int res = compserv.updateCompany(comp);
		
		if(res>0)
		{
			attr.addFlashAttribute("response", "Company updated successfully");
			return "redirect:/viewcompanies";
		}
		else {
			attr.addFlashAttribute("reserr", "Company is not updated ");
			return "redirect:/viewcompanies";
		}
	}
}
