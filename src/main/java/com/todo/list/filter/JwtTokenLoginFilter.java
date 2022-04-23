package com.todo.list.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@WebFilter(urlPatterns = "/account/*")
public class JwtTokenLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("==========JwtTokenLoginFilter before==========");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String authorization = httpServletRequest.getHeader("authorization");
		System.out.println(authorization);
		chain.doFilter(request, response);

		System.out.println("==========JwtTokenLoginFilter after==========");

	}

}