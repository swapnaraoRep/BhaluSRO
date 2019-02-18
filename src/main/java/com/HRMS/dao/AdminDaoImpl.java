package com.HRMS.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{
	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	 protected Session getSession() {
	        return sessionFactory.getCurrentSession();
	    }
	
	public void addEmployee(EmployeeVO e) {
		Session session=sessionFactory.getCurrentSession();
		//session.persist(e);
		session.saveOrUpdate(e);
		
	}
	public void updateEmployee(EmployeeVO e) {
		System.out.println("updateEmployee...."+e.getId()+"...."+e.getEmployeeLogin().getId());
		Session session = this.sessionFactory.getCurrentSession();
		session.update(e);
		logger.info("&&&&&&&&&&&&&&&&&& updated successfully, Person Details="+e);
		
	}

	
	public List<EmployeeVO> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<EmployeeVO> personsList = session.createQuery("from EmployeeVO").list();
		
		for(EmployeeVO p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
		
	}

	
	public EmployeeVO getEmployeeById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		EmployeeVO p = (EmployeeVO) session.load(EmployeeVO.class, new Integer(id));
		logger.info("EmployeeVO loaded successfully, Person details="+p);
		return p;
	}

	
	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		EmployeeVO p = (EmployeeVO) session.load(EmployeeVO.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("EmployeeVO deleted successfully, person details="+p);
		
	}

	public List<DepartmentVO> listDepartments() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<DepartmentVO> deptList = session.createQuery("from DepartmentVO").list();
		System.out.println("*************EEEEEE"+deptList.get(0).getDepartmentId());
		for(DepartmentVO p : deptList){
			logger.info("dept List::::::::::"+p.getDepartmentId());
		}
		return deptList;
	}

	public void addDepartment(DepartmentVO e) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(e);
	}


}
