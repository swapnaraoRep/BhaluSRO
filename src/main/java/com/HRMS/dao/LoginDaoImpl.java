package com.HRMS.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.HRMS.Model.EmployeeVO;


@Repository("loginDao")
public class LoginDaoImpl implements LoginDao
{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	 protected Session getSession() {
	        return sessionFactory.getCurrentSession();
	    }
	
	@Override
	public EmployeeVO validateUser(String uname, String password) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EmployeeVO.class);
		criteria.add(Restrictions.eq("firstName", uname));
		criteria.add(Restrictions.eq("lastName", password));
		EmployeeVO employee = (EmployeeVO) criteria.uniqueResult();
		System.out.println("department Id for login User"+employee.getDepartment_id());
        return employee;
	}

}
