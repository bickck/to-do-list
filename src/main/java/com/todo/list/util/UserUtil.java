package com.todo.list.util;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.todo.list.entity.UserEntity;
import com.todo.list.repository.UserRepository;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저 정보를 검사하고 비밀번호를 인코딩하는 등 CRUD 이외의 기능들을 구현한 문서입니다.
 * 
 */
@Component
public class UserUtil {

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	public String passwordEncoding(String password) {
		String encodePassword = bCryptPasswordEncoder.encode(password);
		return encodePassword;
	}

	public boolean isUsernameDuplicatedCheck(String username) {
		UserEntity user = userRepository.findByUsername(username);

		return (user == null) ? true : false;
	}

	public boolean isPasswordValidCheck(String password) {
		return true;
	}

	public boolean isFindCookie(Cookie[] cookies, String target) {
		int i = 0;
		if (cookies.length == 0)
			return false;

		while (i < cookies.length) {
			Cookie cookie = cookies[i];
			if (target.equals(cookie.getName())) {
				return true;
			}

		}
		return false;
	}

	public int findCookieIndex(Cookie[] cookies, String target) {

		int i = 0;
		if (cookies.length == 0)
			return -1;

		while (i < cookies.length) {
			Cookie cookie = cookies[i];
			if (target.equals(cookie.getName())) {
				return i;
			}

		}
		return -1;
	}
}