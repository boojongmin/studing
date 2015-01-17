package com.school.bank_web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.bank_web.service.LoginService;
import com.school.bank_web.vo.UsersVo;

//@Component @Service @Repository @Controller
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired LoginService service;
	
	
	
	//localhost:8080/bank_web/*.do
	//localhost:8080/bank_web/web/user/test
	@RequestMapping("test")
	public String test(){
		return "/user/test";
	}
	
	@RequestMapping("/info/{userid}")
	public ModelAndView info(@PathVariable String userid){
		ModelAndView mv = new ModelAndView();
		mv.addObject("userid", userid);
		mv.setViewName("/user/info");
		return mv;
	}
	
	@RequestMapping("/infos/{userid}")
	public String infos(@PathVariable String userid, 
				Model model){
		model.addAttribute("userid", userid);
		
		return "/user/info";
	}
	
//	@RequestMapping("/login")
//	public String login(
//			@RequestParam(required= false, value="userid") String userid,
//			Model model ){
//		UsersVo vo = service.doLogin(userid);
//		if(vo == null){
//			model.addAttribute("userid", "아이디가 없습니다.");
//		}else{
//			model.addAttribute("userid", vo.getUserid());
//		}
//		return "/user/info";
//	}
	
	@RequestMapping( method = RequestMethod.GET, value="/login")
	public String login_get(@ModelAttribute("usersVo") UsersVo usersVo){
		return "/user/login";
	}
	
	@RequestMapping( method = RequestMethod.POST, value="/login")
	public String login_post(
			@Valid  @ModelAttribute("usersVo") UsersVo usersVo,
			BindingResult result
			){
		if(result.hasErrors()){
			System.out.println("test");
		}
		return "/user/login";
	}

}
