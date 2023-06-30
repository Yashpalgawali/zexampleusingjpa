package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name="tbl_department")
public class Department {
 
	@Id
	@SequenceGenerator(name = "dept_seq",initialValue =1,allocationSize = 1)
	@GeneratedValue(generator = "dept_seq",strategy = GenerationType.AUTO)
	private Long dept_id;
	
	private String dept_name;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Company.class)
	@JoinColumn(name= "comp_id")
	private Company company;
}
