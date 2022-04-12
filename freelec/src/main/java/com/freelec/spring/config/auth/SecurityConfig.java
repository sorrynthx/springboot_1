package com.freelec.spring.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.freelec.spring.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 	// final �ʵ忡 ���� �����ڸ� ������ִ� lombok�� annotation
@EnableWebSecurity			// Spring Security ���� Ȱ��ȭ
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// h2-console ȭ���� ����ϱ� ���� �ش� �ɼ� disable
			.csrf().disable().headers().frameOptions().disable()
			.and()
				// URL�� ���Ѱ��� ���� �ɼ� ������(authoriezRequests�� ����Ǿ�߸� antMatchers �ɼ� ��� ����)
				.authorizeRequests()
				// ���Ѱ��� ��� ����, "/"�� ������ URL�� ��ü ���� ����
				.antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
				// "/api/v1/** �ּҸ� ���� aPI�� USER ������ ���� ����� ����"
				.antMatchers("/api/v1/**").hasRole(Role.USER.name())
				// ������ ���� �̿� ������ URL���� ��Ÿ�� (������ ����ڸ� ������ URL ���)
				.anyRequest().authenticated()
			.and()
				// �α׾ƿ� ��, "/"���� �̵�
				.logout()
					.logoutSuccessUrl("/")
			.and()
				// OAuth2 �α��� ���
				.oauth2Login()
					// OAuth2 �α��� ���� �� ����� ���� ������ ���� ���� ���
					.userInfoEndpoint()
						// �Ҽ� �α��� ���� ��, �ļ� ��ġ�� ������ UserService �������̽� ����ü ���
						.userService(customOAuth2UserService);
					
	}
	
	
}
