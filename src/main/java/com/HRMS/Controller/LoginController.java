package com.HRMS.Controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.HRMS.Model.EmployeeVO;
import com.HRMS.Service.LoginService;


@Controller
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	private Validator validator;
	
	@Value("${valid.uname}")
	private String sampleName;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	
	@RequestMapping("Login")
	public ModelAndView showLoginPage(Model model)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employeeVO", new EmployeeVO());
		modelAndView.setViewName("AdminLogin");
		return modelAndView;
	}
	@RequestMapping("validUser")
	public ModelAndView validateUser(@ModelAttribute("employeeVO") @Valid EmployeeVO employeeVO,
			BindingResult result,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			Model model)
	{
		ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors())
    	{
        	modelAndView.setViewName("AdminLogin");
        	
    	}
		else
		{
		System.out.println("LoginController"+firstName+""+lastName);
		EmployeeVO employee = loginService.validateUser(firstName, lastName);
		if(employee!=null)
		{
			model.addAttribute(employee);
			model.addAttribute("user", employee);
			modelAndView.setViewName("HRHomePage");
        	
			
			
		}
		else
		{
			modelAndView.setViewName("AdminLogin");
        	
		}
		}
        return modelAndView;
		
	}

}
