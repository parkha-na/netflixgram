package com.github.parkhana.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.parkhana.service.FileService;
import com.github.parkhana.vo.ReplyVo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.parkhana.service.NetService;
import com.github.parkhana.vo.NetVo;

@Controller
public class NetController {
	
	private static final Logger logger = LoggerFactory.getLogger(NetController.class);

	@Autowired
	private NetService netService;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model, NetVo vo) {

		return "redirect:/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, HttpServletRequest request, NetVo vo) {
		String page = StringUtils.defaultIfBlank((String) request.getParameter("page"), "1");
		String ch1 = StringUtils.defaultIfBlank((String) request.getParameter("ch1"), "");
		String ch2 = StringUtils.defaultIfBlank((String) request.getParameter("ch2"), "");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ch1", ch1);
		params.put("ch2", ch2);
		//NetVo k = netService.TOTALREPLY(vo);
		//model.addAttribute("K1", k.getK1());

		int pageNum = Integer.parseInt(page);
		int startPage = ((pageNum - 1) * 10);
		int endPage = (pageNum * 10) - 1;
		params.put("startPage", startPage);
		params.put("endPage", endPage);
		List<NetVo> boardList = netService.selectNetList(params);
		for (NetVo netVo : boardList) {
			NetVo resultVo = netService.TOTALREPLY(netVo);
			netVo.setReplyCnt(resultVo.getReplyCnt());
		}
 		model.addAttribute("li", boardList);

		boolean isNextPage = true;
		int nextPageNum = pageNum + 1;
		int nextStartPage = (nextPageNum - 1) * 10;
		int nextEndPage = (nextPageNum * 10) - 1;
		params.put("startPage", nextStartPage);
		params.put("endPage", nextEndPage);
		List<NetVo> nextBoardList = netService.selectNetList(params);
		if (nextBoardList.size() == 0) {
			isNextPage = false;
		}
		model.addAttribute("isNextPage", isNextPage);
		model.addAttribute("page", pageNum);

		return "list";
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
		netService.updateRecommend(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/net_formOK.do")
	public String net_formOK(HttpServletRequest request, NetVo vo) throws Exception {
//		String path = request.getSession().getServletContext().getRealPath("/net/files/");
//		System.out.println("경로확인 : " + path);

		MultipartFile imgFile = vo.getImgFile();
		String extension = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."), imgFile.getOriginalFilename().length());
		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + extension;
		fileService.fileUpload(imgFile, newFileName);
		
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("HHmmss");
		String timeStr = dayTime.format(time);
		Date now = new Date();
		vo.setUploaddate(now);
		vo.setImg(newFileName);

		int status = netService.insertNet(vo);
		if (status == 1) {
			logger.debug("데이터 정상 입력");
		} else {
			logger.error("데이터 입력 오류");
		}

		return "redirect:/";
	}

	@GetMapping("/imageDownload")
	public void imageDownload(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {

		String path = fileService.getUploadDirPath() + File.separator + fileName;

		byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/deleteNet")
	public String deleteNet(NetVo vo) {
		netService.deleteNet(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(Model model, NetVo vo) {
		model.addAttribute("b", netService.selectNet(vo));
		model.addAttribute("r", netService.selectReplyList(vo));
		
		return "reply";
	}
	
	@RequestMapping(value = "/insertReply")
	public String insertReply(Model model, ReplyVo vo) {
		netService.insertReply(vo);
		return "redirect:/reply?id=" + vo.getBoardId();
	}
}
