package com.HRMS.dao;

import java.util.List;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.Model.Projects;

public interface AdminDao {
	
	public void addEmployee(EmployeeVO e);
	public void addDepartment(DepartmentVO e);
	public void updateEmployee(EmployeeVO e);
	public List<EmployeeVO> listPersons();
	public EmployeeVO getEmployeeById(int id);
	public void removeEmployee(int id);
	public List<DepartmentVO> listDepartments();
	/*Project Module */
	public void addProjects(Projects p);
}
