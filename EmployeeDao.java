package com.revision.javabasedmvc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.hibernate.Transaction;

@Component
public class EmployeeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void addEmployee(Employee employee){
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
		
	}
	
	@Transactional
	public void addLaptop(Laptop laptop){
		Session session = sessionFactory.getCurrentSession();
		session.save(laptop);
		
	}
	
	public void addLaptops(List<Laptop> listOfLaptop){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			for(int i=0; i < 3; i++){
				session.save(listOfLaptop.get(i));
				if(i%3==0){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	@Transactional
	public List<Employee> findEmployee(String tech, String loc, Double sal, Double price){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria_employee = session.createCriteria(Employee.class);
		Criteria criteria_laptop = criteria_employee.createCriteria("laptopList");
		if(!StringUtils.isEmpty(tech)){
			criteria_employee.add(Restrictions.eq("preferredTech", tech));
		}
		
		if(!StringUtils.isEmpty(loc)){
			criteria_employee.add(Restrictions.eq("employeeLocation", loc));
		}
		
		if(sal != null){
			criteria_employee.add(Restrictions.gt("employeeSalary", sal));
		}
		
		if(price != null){
			criteria_laptop.add(Restrictions.gt("laptopPrice", price));
		}
		return criteria_employee.list();
		
		
	}
	
	

}
