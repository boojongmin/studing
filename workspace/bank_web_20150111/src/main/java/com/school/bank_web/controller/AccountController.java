package com.school.bank_web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.bank_web.service.AccountService;
import com.school.bank_web.vo.AccountsVo;
import com.school.bank_web.vo.AjaxVo;
import com.school.bank_web.vo.UsersVo;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired AccountService service;
	
	@RequestMapping("viewMyAccount")
	public String viewMyAccount(HttpSession session, Model model){
		UsersVo usersVo = (UsersVo)session.getAttribute("usersVo");
		if(usersVo ==null){
			return "redirect:/web/user/login";
		}
		List<AccountsVo> list = service.viewMyAccount(usersVo);
		model.addAttribute("list", list);
		return "/account/viewMyAccount";
	}
	
	@RequestMapping("saveAmount")
	public String saveAmount(@ModelAttribute("accountsVo") AccountsVo accountsVo){
		return "/account/saveAmount";
	}
	
	@ResponseBody
	@RequestMapping("checkExistsAccount")
	public AjaxVo checkExistsAccount(@RequestBody AccountsVo accountsVo){
		AjaxVo ajaxVo = service.checkExistsAccount(accountsVo);
		return ajaxVo;
	}
}
