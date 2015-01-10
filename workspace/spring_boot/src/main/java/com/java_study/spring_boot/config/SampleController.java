package com.java_study.spring_boot.config;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java_study.spring_boot.vo.UsersVo;

//@EnableAutoConfiguration
@Controller
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Value("${app.helloworld}")
	public String helloworld;
	
	
	@RequestMapping("/helloworld")
	@ResponseBody
	String home() {
		return helloworld;
	}
	
	@RequestMapping("/test")
	String test() {
		return "test";
	}
	
	@RequestMapping("/json")
	@ResponseBody  UsersVo json() {
		UsersVo vo = new UsersVo();
		vo.setUid(1);
		vo.setUserid("boojongmin");
		vo.setUsername("부종민");
		return vo;
	}
	
	@RequestMapping(value="/valid", method=RequestMethod.GET)
	String valid(@ModelAttribute("usersVo") UsersVo userVo) {
		return "valid";
	}
	
	
	@RequestMapping(value="/valid", method=RequestMethod.POST)
	String valid(@ModelAttribute("usersVo") @Valid UsersVo userVo, BindingResult result) {
//		if(!userVo.getPassword().equals(userVo.getPassword_check())) result.rejectValue("password_check", "error.userVo", "��й�ȣ�� ��й�ȣ(Ȯ��)�� ��ġ���� �ʽ��ϴ�.");
//		 if(result.hasErrors()) {
//	            for(ObjectError error : result.getAllErrors()) {
//	                System.out.println(error.getCode() + " : " + error.getDefaultMessage());
//	            }
//			 
//			 return "user/join/form";
//       } 
		return "valid";
	}
	
	@RequestMapping("json_sender")
	String json_sender() {
		System.out.println("console start---->>>>");
		logger.debug("start---->>>>");
		return "json_sender";
	}
	
	@RequestMapping("requestBody")
	@ResponseBody UsersVo requestBody(@RequestBody UsersVo usersVo) {
		System.out.println("console end---->>>>");
		logger.debug("end---->>>>");
		return usersVo;
	}
	
	
	@RequestMapping("exception")
	void requestBody() throws Exception {
		throw new Exception("오류 발생");
	}
	
	
	
	
}
