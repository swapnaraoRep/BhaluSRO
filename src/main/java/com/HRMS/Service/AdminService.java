package com.HRMS.Service;

import java.util.List;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.Model.EmployeeVOImage;
import com.HRMS.Model.Employee_Leaves;
import com.HRMS.Model.Employee_Projects;
import com.HRMS.Model.Projects;

public interface AdminService {
	public void addEmployee(EmployeeVO e);
	public void addEmployeeImage(EmployeeVOImage e);
	public void addDepartment(DepartmentVO e);
	public void updateEmployee(EmployeeVO e);
	public List<EmployeeVO> listPersons();
	public EmployeeVO getEmployeeById(int id);
	public void removeEmployee(int id);
	public List<DepartmentVO> listDepartments();
	/*Leaves*/
	public List<Employee_Leaves> leaveHistory(int empId);
	/*Project Module Services*/
	public void addProjects(Employee_Projects employeeProjects);
	public List<Projects> projectsList();
	
}
