package com.HRMS.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.Service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	
/*	@RequestMapping("/employees")
	public String loadEmployeePage(@ModelAttribute("employeeVO") EmployeeVO employeeVO,Model model )
	{
		//model.addAttribute("employeeVO", employeeVO);
		model.addAttribute("listEmployees", this.employeeService.listPersons());
		model.addAttribute("DepartmentList",this.employeeService.listDepartments());
		return "viewAndUpdateEmployee";
	}*/
	@RequestMapping("/employees")
	public ModelAndView loadEmployeePage(@ModelAttribute("employeeVO") EmployeeVO employeeVO,Model model )
	{
		//model.addAttribute("employeeVO", employeeVO);
		System.out.println("HELLLOOOOOOOOOOOOOOO");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listEmployees", this.employeeService.listPersons());
	
		List<DepartmentVO> dep=this.employeeService.listDepartments();
		List<Integer> DepartmentList=new ArrayList<Integer>();
		for(DepartmentVO dept:dep)
		{
			DepartmentList.add(dept.getDepartmentId());
		}
		modelAndView.addObject("DepartmentList", DepartmentList);
		//System.out.println("..........."+dep.get(0).getDepartmentId());
		modelAndView.setViewName("viewAndUpdateEmployee");
		return modelAndView;
		
	}
	@RequestMapping("/employee/saveEmployee")
	public String saveEmployee(@ModelAttribute("employeeVO") EmployeeVO employeeVO,Model model )
	{
			this.employeeService.addEmployee(employeeVO);
			return "redirect:/employees";
	}
	 @RequestMapping("/editEmployee/{id}")
		  public String editPerson(@PathVariable("id") int id, Model model){
	        model.addAttribute("employeeVO", this.employeeService.getEmployeeById(id));
	        model.addAttribute("listEmployees", this.employeeService.listPersons());
	  	        return "viewAndUpdateEmployee";
	    }
	 
	 	@RequestMapping("/removeEmployee/{id}")
	    public String removePerson(@PathVariable("id") int id){
			
	        this.employeeService.removeEmployee(id);
	        return "redirect:/employees";
	    }
	 	@RequestMapping("/viewAllEmployees")
		public String listPersons(Model model) {
			model.addAttribute("employeeVO", new EmployeeVO());
			model.addAttribute("listEmployees", this.employeeService.listPersons());
			
			return "viewAndUpdateEmployee";
		}
	 	
	 	
	 	
	 
}
