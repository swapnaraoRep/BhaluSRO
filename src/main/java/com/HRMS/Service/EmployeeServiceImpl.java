package com.HRMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.dao.EmployeeDao;
import com.HRMS.dao.LoginDao;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	@Transactional
	public void addEmployee(EmployeeVO e) {
		System.out.println("EmployeeServiceImpl********* addEmployee"+e.getDepartment_id());
		employeeDao.addEmployee(e);
		
	}

	@Override
	@Transactional
	public void updateEmployee(EmployeeVO e) {
		System.out.println("EmployeeServiceImpl********* updateEmployee"+e.getDepartment_id());
		employeeDao.updateEmployee(e);
		
	}

	@Override
	@Transactional
	public List<EmployeeVO> listPersons() {
		// TODO Auto-generated method stub
		return employeeDao.listPersons();
	}

	@Override
	@Transactional
	public EmployeeVO getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
		
	}

	@Override
	@Transactional
	public void removeEmployee(int id) {
		employeeDao.removeEmployee(id);
		
	}

	@Override
	@Transactional
	public List<DepartmentVO> listDepartments() {
		// TODO Auto-generated method stub
		System.out.println("*************listDepartments");
		return employeeDao.listDepartments();
	}

	@Override
	@Transactional
	public void addDepartment(DepartmentVO e) {
		System.out.println("EmployeeServiceImpl********* addEmployee"+e.getDepartmentId());
		employeeDao.addDepartment(e);
		
	}

}
