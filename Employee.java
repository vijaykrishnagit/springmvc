package com.revision.javabasedmvc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Employees")
public class Employee {
	
	@Id
	private int employeeId;
	
	private String employeeName;
	
	private double employeeSalary;
	
	private String preferredTech;
	
	private String employeeLocation;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Laptop> laptopList = new ArrayList<Laptop>();

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public List<Laptop> getLaptopList() {
		return laptopList;
	}

	public void setLaptopList(List<Laptop> laptopList) {
		this.laptopList = laptopList;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getSalary() {
		return employeeSalary;
	}

	public void setSalary(double salary) {
		this.employeeSalary = salary;
	}

	public String getPreferredTech() {
		return preferredTech;
	}

	public void setPreferredTech(String preferredTech) {
		this.preferredTech = preferredTech;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeSalary="
				+ employeeSalary + ", preferredTech=" + preferredTech + ", employeeLocation=" + employeeLocation + "]";
	}
	
	

}
