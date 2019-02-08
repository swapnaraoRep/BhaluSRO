package com.HRMS.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	 protected Session getSession() {
	        return sessionFactory.getCurrentSession();
	    }
	@Override
	public void addEmployee(EmployeeVO e) {
		Session session=sessionFactory.getCurrentSession();
		//session.persist(e);
		session.saveOrUpdate(e);
		
	}

	@Override
	public void updateEmployee(EmployeeVO e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(e);
		logger.info("Person updated successfully, Person Details="+e);
		
	}

	@Override
	public List<EmployeeVO> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<EmployeeVO> personsList = session.createQuery("from EmployeeVO").list();
		
		for(EmployeeVO p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
		
	}

	@Override
	public EmployeeVO getEmployeeById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		EmployeeVO p = (EmployeeVO) session.load(EmployeeVO.class, new Integer(id));
		logger.info("EmployeeVO loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		EmployeeVO p = (EmployeeVO) session.load(EmployeeVO.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("EmployeeVO deleted successfully, person details="+p);
		
	}

	@Override
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
