package com.github.parkhana.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.parkhana.service.NetService;
import com.github.parkhana.vo.NetVo;

@Controller
public class NetController {
	
	private static final Logger logger = LoggerFactory.getLogger(NetController.class);

	@Autowired
	private NetService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "index";
	}
	
	@RequestMapping("/net_formOK.do")
	public String net_formOK(Model model, NetVo vo) {
		//System.out.println(vo);
		int status = service.insertNet(vo);
		if (status == 1) {
			logger.debug("데이터 정상 입력");
		} else {
			logger.error("데이터 입력 오류");
		}
		return "/net_list.do";
	}
	
	@RequestMapping("/net_list.do")
	public String net_list(Model model) {
		model.addAttribute("li", service.selectNetList());
		
		return "/net/net_list";
	}
}
