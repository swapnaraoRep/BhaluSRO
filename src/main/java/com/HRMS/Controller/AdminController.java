package com.HRMS.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.Model.EmployeeVO_Login;
import com.HRMS.Service.AdminService;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/getEmployeesData")
	public ModelAndView loadEmployeePage(@ModelAttribute("employeeVO") EmployeeVO employeeVO,Model model )
	{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listEmployees", this.adminService.listPersons());
		modelAndView.addObject("DepartmentList", GetDepartmentList(this.adminService.listDepartments()));
		modelAndView.setViewName("viewAndUpdateEmployee");
		return modelAndView;
		
	}
	
	@RequestMapping("/RegisterEmployee")
	public String loadEmployeeRegisterPage(@ModelAttribute("employeeVO") EmployeeVO employeeVO,Model model )
	{
		List<DepartmentVO> dep=this.adminService.listDepartments();
		
		model.addAttribute("DepartmentList", GetDepartmentList(this.adminService.listDepartments()));
		return "EmployeeRegister";
	}
	@RequestMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employeeVO") EmployeeVO employeeVO,BindingResult result,Model model )
	{
		
		if(result.hasErrors())
		{
			return "EmployeeRegister";
		}
		else
		{
		employeeVO.setEmployeeLogin(employeeVO.getEmployeeLogin());
		employeeVO.getEmployeeLogin().setEmployeeVO(employeeVO);
	
		this.adminService.addEmployee(employeeVO);
		}
			return "redirect:/Admin/getEmployeesData";
	}
	
	@RequestMapping("/editEmployee/{id}")
	  public String editEmployee(@PathVariable("id") int id, Model model){
      model.addAttribute("employeeVO", this.adminService.getEmployeeById(id));
      model.addAttribute("listEmployees", this.adminService.listPersons());
      
      model.addAttribute("DepartmentList",GetDepartmentList(this.adminService.listDepartments()));
	        return "viewAndUpdateEmployee";
  }

	@RequestMapping("/removeEmployee/{id}")
  public String removeEmployee(@PathVariable("id") int id){
		
      this.adminService.removeEmployee(id);
      return "redirect:/Admin/getEmployeesData";
  }
	@RequestMapping("/viewAllEmployees")
	public String listPersons(Model model) {
		model.addAttribute("employeeVO", new EmployeeVO());
		model.addAttribute("listEmployees", this.adminService.listPersons());
		return "viewAllEmployees";
	}
	
	
	public List<Integer> GetDepartmentList(List<DepartmentVO> list)
	{
		List<Integer> DepartmentList=new ArrayList<Integer>();
		for(DepartmentVO dept:list)
		{
			DepartmentList.add(dept.getDepartmentId());
		}
		return DepartmentList;
		
		
	}
	
}
