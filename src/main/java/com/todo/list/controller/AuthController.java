package com.todo.list.controller;

import javax.security.sasl.AuthenticationException;
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

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.user.UserService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private UserService userService;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginRequest(@RequestBody UserDTO userDTO) throws AuthenticationException {

		UserEntity user = userService.userLogin(userDTO);
		String userToken = jwtLoginToken.makeToken(user);

		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}

	@ResponseBody
	@PostMapping("/register")
	public synchronized ResponseEntity<String> registerRequest(@RequestBody UserDTO userDTO) {

		userService.userSave(userDTO);

		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutRequest(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO dto) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
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
//				throw new IllegalAccessError("????????? ????????????");
//			}
//
//			HttpSession reqSession = httpServletRequest.getSession(); // ?????? ??????
//			String sessionId = reqSession.getId(); // ?????? ????????? ??????
//			System.out.println("????????? Session ID : " + sessionId);
//
//			httpSession.setAttribute(sessionId, userDTO.getUsername()); // ?????? ???????????? ???????????? user?????? ??????
//
//			CookieGenerator cookieGenerator = new CookieGenerator();
//			cookieGenerator.setCookieName(SEESION_NAME);
//			cookieGenerator.addCookie(httpServletResponse, sessionId); // ????????????????????? ???????????? ????????? ??????
//		}
//		return "redirect:http://127.0.0.1:5501/index.html";
//	}

}
