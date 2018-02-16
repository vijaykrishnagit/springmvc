package com.revision.javabasedmvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JavaController {
	
@Autowired
private EmployeeDao employeeDao;	
		
@PostMapping("addemployee")
public String addEmployee(EmployeeDetails employeeDetails){
	int employeeId = employeeDetails.getEmployeeId();
	String employeeName = employeeDetails.getEmployeeName();
	String employeeLocation = employeeDetails.getEmployeeLocation();
	double employeeSal = employeeDetails.getEmployeeSalary();
	String employeeTech = employeeDetails.getPreferredTech();
	
	Employee emp = new Employee();
    emp.setEmployeeId(employeeId);
    emp.setEmployeeLocation(employeeLocation);
    emp.setEmployeeName(employeeName);
    emp.setEmployeeSalary(employeeSal);
    emp.setPreferredTech(employeeTech);
    
    List<Laptop> listOfLaptop = new ArrayList<Laptop>();
    Laptop lap1 = null, lap2 = null, lap3 = null;
    if(employeeDetails.getLaptop1Id() != null){
    	lap1 = new Laptop();
    	lap1.setLaptopId(employeeDetails.getLaptop1Id());
    	lap1.setLaptopName(employeeDetails.getLaptop1Name());
    	lap1.setLaptopPrice(employeeDetails.getLaptop1Price());
    	lap1.setEmployee(emp);
    	listOfLaptop.add(lap1);
    	
    }
    if(employeeDetails.getLaptop2Id() != null){
    	lap2 = new Laptop();
    	lap2.setLaptopId(employeeDetails.getLaptop2Id());
    	lap2.setLaptopName(employeeDetails.getLaptop2Name());
    	lap2.setLaptopPrice(employeeDetails.getLaptop2Price());
    	lap2.setEmployee(emp);
    	listOfLaptop.add(lap2);
    }
    if(employeeDetails.getLaptop3Id() != null){
    	lap3 = new Laptop();
    	lap3.setLaptopId(employeeDetails.getLaptop3Id());
    	lap3.setLaptopName(employeeDetails.getLaptop3Name());
    	lap3.setLaptopPrice(employeeDetails.getLaptop3Price());
    	lap3.setEmployee(emp);
    	listOfLaptop.add(lap3);
    }
    
    emp.setLaptopList(listOfLaptop);
    
    
//	emp.setEmployeeLocation(employeeDetails.getEmployeeLocation());
//	Laptop lap = employeeDetails.getLaptop();
    if(emp != null){
    	employeeDao.addEmployee(emp);
    }
    if(lap1 != null){
    	employeeDao.addLaptop(lap1);
    }
    if(lap2 != null){
    	employeeDao.addLaptop(lap2);
    }
    if(lap3 != null){
    	employeeDao.addLaptop(lap3);
    }
//      if(listOfLaptop.size() > 0){
//    	  employeeDao.addLaptops(listOfLaptop);
//      }
	
	return "result.jsp";
}

@GetMapping("findemployee")
public ModelAndView findEmployee(@RequestParam(value="tech", required=false) String tech, @RequestParam(value="location", required=false) String loc, @RequestParam(value="salary", required=false) Double sal, @RequestParam(value="price", required=true) Double price){
	List<Employee> listOfEmp = employeeDao.findEmployee(tech, loc, sal, price);
	ModelAndView mv = new ModelAndView();
	mv.setViewName("listemployee.jsp");
	mv.addObject("emplist", listOfEmp);
	return mv;
}

}
