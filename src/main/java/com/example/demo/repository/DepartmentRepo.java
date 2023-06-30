package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Department;

@Repository("deptrepo")
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	
	@Query(value="SELECT * FROM tbl_department JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id WHERE tbl_department.comp_id=?1",nativeQuery = true)
	//@Query("SELECT d FROM Department d JOIN d.company WHERE d.company.comp_id=?1")
	public List<Department> getDepartmentByCompid(Long id);
	
	@Query("SELECT d FROM Department d JOIN d.company")
	public List<Department> getAllDepartments();
	
	@Transactional
	@Modifying
	@Query("UPDATE Department d SET d.dept_name=?2,d.company.comp_id=?3 WHERE d.dept_id=?1")
	public int updateDepartment(Long did,String dname,Long cid);
}
