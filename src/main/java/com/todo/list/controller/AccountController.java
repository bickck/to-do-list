package com.todo.list.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.CookieGenerator;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.service.user.UserService;
import com.todo.list.util.UserUtil;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";

	private UserService userService;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	public AccountController(UserService userService) {
		this.userService = userService;
	}

//	@PostMapping("/login")
//	public String loginRequest(@ModelAttribute UserDTO userDTO, HttpSession httpSession,
//			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//
//		Cookie[] cookies = httpServletRequest.getCookies();
//
//		if (cookies == null || userUtil.isFindCookie(cookies, SEESION_NAME)) {
//
//			UserEntity entity = userService.userLogin(userDTO);
//			if (entity == null) {
//				throw new IllegalAccessError("로그인 해주세요");
//			}
//
//			HttpSession reqSession = httpServletRequest.getSession(); // 세션 생성
//			String sessionId = reqSession.getId(); // 세션 아이디 확인
//			System.out.println("생성된 Session ID : " + sessionId);
//
//			httpSession.setAttribute(sessionId, userDTO.getUsername()); // 세션 아이디를 이름으로 user정보 저장
//
//			CookieGenerator cookieGenerator = new CookieGenerator();
//			cookieGenerator.setCookieName(SEESION_NAME);
//			cookieGenerator.addCookie(httpServletResponse, sessionId); // 클라이언트에게 세션값을 쿠키로 반환
//		}
//		return "redirect:http://127.0.0.1:5501/index.html";
//	}
	@PostMapping("/login")
	public ResponseEntity loginRequest(@RequestBody UserDTO userDTO, HttpSession httpSession,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		UserEntity user = userService.userLogin(userDTO);
		System.out.println(userDTO.toString());
		String userToken = null;

		if (user != null) {
			userToken = jwtLoginToken.makeToken(user);
		} else {
			throw new IllegalAccessError();
		}
		return new ResponseEntity(userToken, HttpStatus.OK);
	}


	@PostMapping("/register")
	public String registerRequest(@ModelAttribute UserDTO userDTO, HttpServletResponse httpServletResponse) {
		userService.userSave(userDTO);

		return "redirect:http://127.0.0.1:5501/index.html";
	}

	@PostMapping("/logout")
	public String logoutRequest(@RequestBody UserDTO userDTO) {
		return "redirect:http://127.0.0.1:5501/index.html";
	}

}