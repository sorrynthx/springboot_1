package com.freelec.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.freelec.spring.config.auth.LoginUser;
import com.freelec.spring.config.auth.dto.SessionUser;
import com.freelec.spring.service.posts.PostsService;
import com.freelec.spring.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostsService postsService;
	
	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		model.addAttribute("posts", postsService.findAllDesc());
		
		//SessionUser user = (SessionUser) httpSession.getAttribute("user"); -> 어노테이션으로 대체
		
		model.addAttribute("posts", postsService.findAllDesc());
		
		if (user != null) {
            model.addAttribute("loginUser", user.getName());
        }
		
		return "index";
	}
	
	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		
		return "posts-update";
	}
}
