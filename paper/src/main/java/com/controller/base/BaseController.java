package com.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@MultipartConfig
@Controller
public class BaseController {
	@Autowired
	public HttpServletRequest request;
	@Autowired
	public HttpServletResponse response;
	@Autowired
	public HttpSession session;
}
