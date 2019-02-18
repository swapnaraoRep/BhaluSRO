package com.HRMS.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.HRMS.Model.DepartmentVO;
import com.HRMS.Model.EmployeeVO;
import com.HRMS.Model.EmployeeVO_Login;
import com.HRMS.Model.Employee_Leaves;
import com.HRMS.Model.Leaves;
import com.HRMS.Service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	
	@Value("${valid.uname}")
	private String sampleName;
	 	
	 	@RequestMapping("/viewProfile/{id}")
		  public String getEmployeeById(@PathVariable("id") int id,@ModelAttribute("user") EmployeeVO employeeVO, Model model){
	 	
	 		/*System.out.println(":::::::::::::"+employeeVO.getId());
	        model.addAttribute("listEmployees", this.employeeService.getEmployeeById(id));
	        model.addAttribute("user", this.employeeService.getEmployeeById(id));*/

	  	        return "viewEmployeeProfile";
	    }
	 	@RequestMapping("/LoadchangePassword/{id}")
		public String changePassword(@PathVariable("id") int id,@ModelAttribute("user") EmployeeVO employeeVO,Model model) {
	 		System.out.println("employeeVO...."+employeeVO.getId());
			model.addAttribute("user", new EmployeeVO());
			return "changePasswordPage";
		}
	 	@RequestMapping("/viewProfile")
		  public String getEmployeeProfile(){
	  	        return "viewEmployeeProfile";
	    }
	 	@RequestMapping("/LoadChangePassword")
		public ModelAndView loadChangePassword() {
	 		ModelAndView modelAndView = new ModelAndView();
	 		modelAndView.addObject("employeeLogin", new EmployeeVO_Login());
	 		modelAndView.setViewName("changePasswordPage");
			return modelAndView;
		}
	 	@RequestMapping("/UpdatePassword/{id}")
		public ModelAndView updatePassword(@Valid @ModelAttribute("employeeLogin") EmployeeVO_Login employeeLogin,BindingResult result,
				@PathVariable("id") int id
			
		) {
	 		
	 		System.out.println("^^^^^^^^^^^^^"+sampleName+result);
	 		ModelAndView modelAndView = new ModelAndView();
	 		//System.out.println("*******"+oldPassword+"...."+password);
	 		if (result.hasErrors()) {
	 			System.out.println("**********444444444**********"+result.getFieldError());
	 			modelAndView.setViewName("changePasswordPage");
	 		}
	 		else
	 		{
	 			
	 				if(employeeLogin.getPassword().equalsIgnoreCase(employeeLogin.getConfirmPassword()))
	 				{
	 					System.out.println("password and confirm are equal"+employeeLogin.getConfirmPassword()+""+employeeLogin.getPassword());
	 					employeeService.updateEmployeePassword(employeeLogin);
	 					modelAndView.addObject("PasswordFlag", employeeLogin.getUserName()+"Password Updated"+" Successfully");
	 					modelAndView.setViewName("changePasswordPage");
	 				}
	 				else
	 				{
	 					result.rejectValue("confirmPassword", "password.MisMatch");
	 					modelAndView.setViewName("changePasswordPage");
	 				}
		 		
	 		}
	 		
	 	
			return modelAndView;
		}
	 	@RequestMapping("/LeaveRequest")
		public String LeaveRequest(Model model) {
	 		Leaves l= new Leaves();
	 	
	 		l.setLeaveName("Maternity");
	 		l.setDays(2);
	 		employeeService.addLeaves(l);
	 		ArrayList<Integer> LeaveList=new ArrayList<Integer>();
		 	LeaveList.add(1);
		 	LeaveList.add(2);
		 	model.addAttribute("LeaveList", LeaveList);

			model.addAttribute("employeeLeave", new Employee_Leaves());
			return "leaveRequest";
		}
	 	@RequestMapping("/sendLeaveRequest")
		public String sendLeaveRequest(@ModelAttribute("employeeLeave") Employee_Leaves employeeLeave,Model model) {
	 	ModelAndView modelandView=new ModelAndView();
	 	System.out.println("employeeLeave"+employeeLeave.getLeaves().getLeaveId());
	 	employeeService.sendLeaveRequest(employeeLeave);
	 	model.addAttribute("Success","successFullyAdded"+employeeLeave.getEmpId());
			return "leaveRequest";
		}
	 	@RequestMapping("/leaveHistoryRequest")
		public ModelAndView LeaveHistory(@ModelAttribute("employeeLeave") Employee_Leaves employeeLeave,Model model) {
	 	ModelAndView modelandView=new ModelAndView();
	 	
	 	modelandView.addObject("employeeLeave", new Employee_Leaves());
	 	modelandView.setViewName("leaveHistoryRequest");
			return modelandView;
		}
	 	
	 	@RequestMapping("/searchLeaveHistory")
		public ModelAndView findLeaveHistory(@RequestParam("id") int id,@ModelAttribute("employeeLeave") Employee_Leaves employeeLeave,Model model) {
	 	ModelAndView modelandView=new ModelAndView();
	 	System.out.println("nameeeee"+id);
	 	modelandView.addObject("employeeLeave", new Employee_Leaves());
	 	List<Employee_Leaves> history=employeeService.leaveHistory(id);
	 	model.addAttribute("history", history);
	 	modelandView.setViewName("leaveHistoryRequest");
			return modelandView;
		}
	 /*	@RequestMapping("/employee1-1")
		public String addEmployeeAndRole(@ModelAttribute("employee") EmployeeVO employeeVO1,Model model)
		{
		
		//1-1 bidirectinal w/o joinColumn
	 		EmployeeVO employeeVO=new EmployeeVO ();
			employeeVO.setFirstName("Hello");
			employeeVO.setLastName("checking");
			//employeeVO.setEmail("Take@gmail.com");
			employeeVO.setDateOfBirth("2013-08-18");
			employeeVO.setHire_date("2013-08-18");
			employeeVO.setDepartment_id("1");
			System.out.println("**********"+employeeVO.getFirstName());
			EmployeeVO_Login login=new EmployeeVO_Login();
			
			login.setConfirmPassword("password");
			//login.setUserName(employeeVO.getEmail());
			login.setPassword("password");
			login.setRole("Role");
			employeeVO.setEmployeeLogin(login);
			employeeVO.getEmployeeLogin().setEmployeeVO(employeeVO);
			
			
			this.employeeService.addEmployee(employeeVO);
			
			return "helloWorld";
		}
	 	
	 	@RequestMapping("/UpdatePassword")
		public String changePassword(@PathVariable("id") int id,@ModelAttribute("user") EmployeeVO employeeVO,Model model) {
	 		System.out.println("employeeVO...."+employeeVO.getId());
			model.addAttribute("user", new EmployeeVO());
			return "changePasswordPage";
		}
	 	@RequestMapping("/AddLeave")
		public String addLeave(Model model) {
			model.addAttribute("leaves", new Leaves());
			Leaves l =new Leaves();
			l.setLeaveName("Casual");
			l.setDays(12);
			this.employeeService.addLeave(l);
			return "viewEmployeeProfile";
		}
	 	EmployeeVO_Login2 employeeLogin=new EmployeeVO_Login2();
		employeeLogin.setFirstName("checking");
		employeeLogin.setPassword("checking");
		employeeLogin.setRole("checking");
		EmployeeVO2 employee= employeeVO.getEmployeeLogin().getEmployee();
		
		 if (employee == null)
			 employee = new EmployeeVO2(employeeLogin); 
		
		 System.out.println("((((((((((((("+employee.getFirstName());
		 */
}
