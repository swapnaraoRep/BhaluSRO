package com.HRMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.Model.Projects;
import com.HRMS.dao.AdminDao;
import com.HRMS.dao.EmployeeDao;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void addEmployee(EmployeeVO e) {
		adminDao.addEmployee(e);
		
	}
	public void updateEmployee(EmployeeVO e) {
		System.out.println("EmployeeServiceImpl********* updateEmployee"+e.getId()+"...."+e.getEmployeeLogin().getId());
		adminDao.updateEmployee(e);
		
	}

	
	
	public List<EmployeeVO> listPersons() {
		// TODO Auto-generated method stub
		return adminDao.listPersons();
	}

	
	
	public EmployeeVO getEmployeeById(int id) {
		return adminDao.getEmployeeById(id);
		
	}

	
	
	public void removeEmployee(int id) {
		adminDao.removeEmployee(id);
		
	}

	
	
	public List<DepartmentVO> listDepartments() {
		// TODO Auto-generated method stub
		System.out.println("*************listDepartments");
		return adminDao.listDepartments();
	}

	
	public void addDepartment(DepartmentVO e) {
		System.out.println("EmployeeServiceImpl********* addEmployee"+e.getDepartmentId());
		adminDao.addDepartment(e);
		
	}

	@Override
	public void addProjects(Projects p) {
		adminDao.addProjects(p);
		
	}


}
