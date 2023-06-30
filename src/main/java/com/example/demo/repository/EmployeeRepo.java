package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository("emperpo")
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_employee SET emp_name=?1,emp_email=?2,dept_id=?3,desig_id=?4,emp_status=?5 where emp_id=?6",nativeQuery = true)
	public int updateEmployee(String ename,String email,Long status,Long depid,Long desid,Long empid);
	
	
	@Query(value="SELECT * from tbl_employee JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id",nativeQuery = true)
	//@Query("SELECT e FROM Employee e JOIN e.department JOIN e.department.company")
	public List<Employee> getAllEmployees();
	
	
	@Query("SELECT e FROM Employee e JOIN e.department JOIN e.department.company JOIN e.designation WHERE e.emp_id=?1")
	public List<Employee> getDeptByEmpId(Long eid);
	
}