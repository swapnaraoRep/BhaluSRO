package com.HRMS.Service;

import java.util.List;

import com.HRMS.Model.EmployeeVO;

public interface LoginService {
	
	public EmployeeVO validateUser(String uname,String password);
	

}
