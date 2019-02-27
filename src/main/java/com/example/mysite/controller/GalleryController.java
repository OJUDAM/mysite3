package com.example.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.mysite.vo.SiteVo;
import com.example.security.Auth;
import com.example.security.Auth.Role;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	//@Autowired
	//private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		//List list = galleryService.getGalleryList();
		//model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/upload")
	public String updload(
		@ModelAttribute SiteVo siteVo,
		@RequestParam(value="upload-image") MultipartFile multipartFile) {
		
		return "redirect:/gallery";
	}

}
