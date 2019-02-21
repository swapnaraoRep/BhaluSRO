package com.HRMS.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.HRMS.Model.Employee_Projects;
import com.HRMS.Model.Projects;
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
		Object[] departmentListToArray = this.adminService.listDepartments().toArray();
	      List<Object> departmentArrayToList = Arrays.asList(departmentListToArray);
	      modelAndView.addObject("DepartmentList",GetDropDownData(departmentArrayToList,"department"));	
	      modelAndView.setViewName("viewAndUpdateEmployee");
		return modelAndView;
		
	}
	
	@RequestMapping("/RegisterEmployee")
	public String loadEmployeeRegisterPage(@ModelAttribute("employeeVO") EmployeeVO employeeVO,Model model )
	{
		List<DepartmentVO> dep=this.adminService.listDepartments();
		Object[] departmentListToArray = this.adminService.listDepartments().toArray();
	      List<Object> departmentArrayToList = Arrays.asList(departmentListToArray);
	      model.addAttribute("DepartmentList",GetDropDownData(departmentArrayToList,"department"));	
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
      
      Object[] departmentListToArray = this.adminService.listDepartments().toArray();
      List<Object> departmentArrayToList = Arrays.asList(departmentListToArray);
      model.addAttribute("DepartmentList",GetDropDownData(departmentArrayToList,"department"));	
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
	/*Project Module*/
	@RequestMapping("/Projects")
	public ModelAndView LoadProject(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("employeeProjects", new Employee_Projects());
		modelAndView.setViewName("ProjectModule");
		return modelAndView;
		
		
	}
	@RequestMapping("/AssignProjectRequest")
	public String loadProjectRegisterPage(@ModelAttribute("employeeProjects") Employee_Projects employeeProjects ,Model model )
	{
		Object[] employeeListToArray = this.adminService.listPersons().toArray();
	      List<Object> employeeArrayToList = Arrays.asList(employeeListToArray);
		model.addAttribute("EmployeeList", GetDropDownData(employeeArrayToList,"employeeId"));
		
		return "AssignProjectRequest";
	}
	
	/*@RequestMapping("/saveProject")
	public String saveProject(@Valid @ModelAttribute("employeeProjects") Employee_Projects employeeProjects,BindingResult result,Model model )
	{
		
		if(result.hasErrors())
		{
			return "ProjectRegister";
		}
		else
		{

	
		this.adminService.addProjects(employeeProjects);
		}
			return "redirect:/Admin/getEmployeesData";
	}*/
	
	
	public List<Integer> GetDropDownData(List<Object> list,String type)
	{
		List<Integer> dataList=new ArrayList<Integer>();

		if(type.equalsIgnoreCase("department"))
		{
			for(Object dept:list)
			{
				DepartmentVO department=(DepartmentVO)dept;
				dataList.add(department.getDepartmentId());
			}
			
		}
		else
		{
			for(Object emp:list)
			{
				EmployeeVO department=(EmployeeVO)emp;
				dataList.add(department.getId());
			}	
		}
		
		return dataList;
	}
	
}
