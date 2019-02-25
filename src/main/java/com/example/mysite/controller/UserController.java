package com.example.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mysite.service.UserService;
import com.example.mysite.vo.UserVo;

@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping( value="/join", method=RequestMethod.GET )
	public String join(){
		return "user/join";
	}
	
	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join( @ModelAttribute UserVo userVo ){
		userService.join( userVo );
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}

	@RequestMapping( "/joinsuccess" )
	public String joinsuccess(){
		return "user/joinsuccess";
	}
	

	@RequestMapping( value="/modify", method=RequestMethod.GET )
	public String modify(HttpSession session, Model model ){
		/* 접근제어 */
		UserVo authUser = (UserVo)session.getAttribute("AuthUser");
		if(authUser == null) {
			return "rediurect:/";
		}
				
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/modify";
	}
	
	@RequestMapping( value="/modify", method=RequestMethod.POST )
	public String modify( 
		HttpSession session, 
		@ModelAttribute UserVo userVo ){
		UserVo authUser = (UserVo)session.getAttribute("AuthUser");
		if(authUser == null) {
			return "rediurect:/";
		}

		userVo.setNo( authUser.getNo() );
		userService.modifyUser( userVo );
		
		return "redirect:/user/modify?result=success";
	}
}
