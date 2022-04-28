package com.github.parkhana.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.github.parkhana.service.NetService;
import com.github.parkhana.vo.NetVo;

@Controller
public class NetController {
	
	private static final Logger logger = LoggerFactory.getLogger(NetController.class);

	@Autowired
	private NetService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model, NetVo vo) {
		model.addAttribute("li", service.selectNetList(vo));

		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale) {

		return "login";
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(Locale locale, Model model) {
		return "post";
	}
	
	@RequestMapping(value = "/updateRecommend", method = RequestMethod.GET)
	public String updateRecommend(NetVo vo) {
		service.updateRecommend(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/net_formOK.do")
	public String net_formOK(HttpServletRequest request, NetVo vo) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/net/files/");
		System.out.println("경로확인 : " + path);
		
		MultipartFile  imgUpdateFile = vo.getImgFile();
		String fileName =imgUpdateFile.getOriginalFilename();
		File f = new File(path+fileName);
		String onlyFilename = "";
		String extension = "";
		
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime=new SimpleDateFormat("HHmmss");
		String timeStr = dayTime.format(time);
		Date now = new Date();
		if (!imgUpdateFile.isEmpty()) {
			if (f.exists()) {
				// 중복 파일이 존재하면
				System.out.println("동일한 파일 존재하는 경우");
				onlyFilename=fileName.substring(0, fileName.indexOf("."));
				extension=fileName.substring(fileName.indexOf("."));
				fileName =  onlyFilename + "_" + timeStr + extension ;
				imgUpdateFile.transferTo(new File(path+fileName));
			} else {
				// 중복 파일이 존재하지 않으면
				imgUpdateFile.transferTo(new File(path+fileName));
				System.out.println("fileName:" + fileName);
		  	}
		}
		
		vo.setUploaddate(now);
		vo.setImg(fileName);
		
		//System.out.println(vo);
		int status = service.insertNet(vo);
		if (status == 1) {
			logger.debug("데이터 정상 입력");
		} else {
			logger.error("데이터 입력 오류");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/deleteNet")
	public String deleteNet(NetVo vo) {
		service.deleteNet(vo);
		return "redirect:/";
	}

	
//	@RequestMapping("/net_list.do")
//	public String net_list(Model model) {
//		model.addAttribute("li", service.selectNetList());
//
//		return "/net/net_list";
//	}
}
