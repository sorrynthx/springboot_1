package com.freelec.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelec.spring.web.dto.HelloResponseDto;

@RestController	// 컨트롤러를 JSON으로 반환하는 컨트롤러 (@ResponsBody를 한번에 사용)
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/hello/dto")
	public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
		return new HelloResponseDto(name, amount);
	}
}
