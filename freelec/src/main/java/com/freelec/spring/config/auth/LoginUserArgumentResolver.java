package com.freelec.spring.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.freelec.spring.config.auth.dto.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
	
	private final HttpSession httpSession;
	
	// ��Ʈ�ѷ� �޼����� Ư�� �Ķ���͸� �����ϴ��� �Ǵ�
	// �Ķ���Ϳ� @LoginUser ������̼��� �پ� �ְ�, �Ķ���� Ŭ���� Ÿ���� SessionUser.class�� ��� true ��ȯ
	@Override
	public boolean supportsParameter(MethodParameter parameter) {	
		
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		
		System.out.println("=====> Session.class: " 			   + SessionUser.class);
		System.out.println("=====> parameter.getParameterType(): " + parameter.getParameterType());
		
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		System.out.println("=====> supportsParameter: " + isLoginUserAnnotation + " / " +  isUserClass);
		return isLoginUserAnnotation && isUserClass;
	}
	
	// �Ķ���Ϳ� ������ ��ü�� ����
	// ���⼭�� ���ǿ��� ��ü�� �����´�
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		return httpSession.getAttribute("user");
	}
}
