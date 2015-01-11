package com.school.bank_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@RequestMapping("test")
	public String test(){
		return "/test";
	}

}
