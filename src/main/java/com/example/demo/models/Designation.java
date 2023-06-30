package com.example.demo.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tbl_designation")
public class Designation {

	@Id
	@SequenceGenerator(name = "desig_seq",initialValue =1,allocationSize = 1)
	@GeneratedValue(generator = "desig_seq",strategy = GenerationType.AUTO)
	private Long desig_id;
	
	private String desig_name;
	
	
}
