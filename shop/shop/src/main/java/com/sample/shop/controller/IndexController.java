package com.sample.shop.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.shop.dto.ItemResponseDto;

@Controller
@RequestMapping("/shop")
public class IndexController {
	
	@GetMapping(value="/index")
	public String index(Model model) {
		
		model.addAttribute("data", "Welcome! 환영합니다.");
		
		return "html/index";
	}
	
	@GetMapping(value="/detail")
	public String detail(Model model) {
		
		ItemResponseDto itemResDto = new ItemResponseDto();
		itemResDto.setItemDetail("상품 상세 설명 ");
		itemResDto.setItemNm("테스트 상품 1 ");
		itemResDto.setPrice(10000);
		itemResDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemResDto);
		return "html/detail";
	}
	
	@GetMapping(value="/content")
	public String content(Model model) {
		return "html/ex7";
	}
	
}
