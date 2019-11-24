package com.springboot.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.springboot.base.model.BaseIdEntity;

/**
 * @author Satish
 *
 */
@Entity
public class Employee extends BaseIdEntity {

	@Column(name = "name")
	private String employeeName;

	@Column(name = "designation")
	private String designation;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Employee(String employeeName, String designation) {
		super();
		this.employeeName = employeeName;
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", designation=" + designation + "]";
	}

	public Employee() {
	}

}
