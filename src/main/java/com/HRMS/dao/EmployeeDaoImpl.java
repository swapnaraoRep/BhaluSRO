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
import com.HRMS.Model.EmployeeVO_Login;
import com.HRMS.Model.Employee_Leaves;
import com.HRMS.Model.Leaves;

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
	
	

	
	public EmployeeVO getEmployeeById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		EmployeeVO p = (EmployeeVO) session.load(EmployeeVO.class, new Integer(id));
		logger.info("EmployeeVO loaded successfully, Person details="+p);
		return p;
	}

	public List<DepartmentVO> listDepartments() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<DepartmentVO> deptList = session.createQuery("from DepartmentVO").list();
		for(DepartmentVO p : deptList){
			logger.info("dept List::::::::::"+p.getDepartmentId());
		}
		return deptList;
	}

	public void addDepartment(DepartmentVO e) {
		getSession().saveOrUpdate(e);
	}

	@Override
	public void updateEmployeePassword(EmployeeVO_Login employeeLogin) {
		System.out.println("updateEmployeePassword.......");
		getSession().update(employeeLogin);		
	}
	public void addLeaves(Leaves l) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("From DaoIMPLLLLLLLLLLLL"+l.getDays()+"id"+l.getLeaveId());
session.persist(l);

	}

	public void sendLeaveRequest(Employee_Leaves employeeLeave) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("sendLeaveRequest DaoIMPLLLLLLLLLLLL"+employeeLeave.getTotal_days()+"id"+employeeLeave.getLeaves());
session.persist(employeeLeave);
		
	}

	public List<Employee_Leaves> leaveHistory(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//List<Employee_Leaves> leaveHistory = session.createQuery("from Employee_Leaves").list();
		//List<Employee_Leaves> leaveHistory = (List<Employee_Leaves>) session.load(Employee_Leaves.class, new Integer(id));
		//List<Employee_Leaves> leaveHistory = session.createQuery("from Employee_Leaves where empId="+id).list();
		//List<Employee_Leaves> leaveHistory= session.createQuery("from Employee_Leaves l join fetch l.leaves where l.leaveId =l.leaves.leaveId and l.empId="+id).list();

List<Employee_Leaves> leaveHistory=session.createSQLQuery("select * from hrms_employee_leaves as l,leaves  as v where l.leaveId=v.leaveId and empId="+id).list();
		for(Employee_Leaves history :leaveHistory)
{
	System.out.println("history"+history.getEmpId()+"*****"+history.getTotal_days());
}
		
		return leaveHistory;
	}

}
