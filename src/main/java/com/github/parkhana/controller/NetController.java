package com.github.parkhana.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
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

import com.github.parkhana.service.FileService;
import com.github.parkhana.service.NetService;
import com.github.parkhana.service.UserService;
import com.github.parkhana.vo.NetVo;
import com.github.parkhana.vo.ReplyVo;
import com.github.parkhana.vo.Users;

@Controller
public class NetController {
	
	private static final Logger logger = LoggerFactory.getLogger(NetController.class);

	@Autowired
	private NetService netService;

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model, NetVo vo) {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (StringUtils.equalsIgnoreCase(netService.getServerLocation(), "server")) {
			model.addAttribute("redirectURI", "http://49.247.38.216:8081/callback");
			model.addAttribute("clientId", "MCfxN6QafHKnz4o_H26m");
		} else {
			model.addAttribute("redirectURI", "http://localhost:8081/callback");
			model.addAttribute("clientId", "TAMKGPSU19GpuoJbmZHE");
		}
		if (session == null) {
			return "login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser != null) {	/* 세션에 회원 데이터가 있으면 리스트 페이지로 이동 */
			return "redirect:/list";
		}

		return "login";
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 로그인 페이지로 이동 */
			return "redirect:/login";
		}
		model.addAttribute("loginUser", loginUser);
		return "post";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, HttpServletRequest request, NetVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 로그인 페이지로 이동 */
			return "redirect:/login";
		}
		model.addAttribute("loginUser", loginUser);

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

	@RequestMapping(value = "/updateRecommend", method = RequestMethod.GET)
	public String updateRecommend(NetVo vo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 홈으로 이동 */
			return "redirect:/login";
		}
		netService.updateRecommend(vo);
		return "redirect:/list";
	}
	
	@RequestMapping("/net_formOK.do")
	public String net_formOK(HttpServletRequest request, NetVo vo) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 홈으로 이동 */
			return "redirect:/login";
		}

		MultipartFile imgFile = vo.getImgFile();
		if (imgFile != null) {
			String originalFilaName = imgFile.getOriginalFilename();
			if (originalFilaName.length() > 0) {
				String extension = originalFilaName.substring(imgFile.getOriginalFilename().lastIndexOf("."), originalFilaName.length());
				UUID uuid = UUID.randomUUID();
				String newFileName = uuid.toString() + extension;
				fileService.fileUpload(imgFile, newFileName);
				vo.setImg(newFileName);
			}
		}
		
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("HHmmss");
		String timeStr = dayTime.format(time);
		Date now = new Date();
		vo.setUploaddate(now);

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
	public String deleteNet(HttpServletRequest request, NetVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 로그인 페이지로 이동 */
			return "redirect:/login";
		}
		netService.deleteReply(vo);
		netService.deleteNet(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(HttpServletRequest request, Model model, NetVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 로그인 페이지로 이동 */
			return "redirect:/login";
		}
		int id = vo.getId();
		if (id == 0) {
			return "redirect:/list";
		}
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("b", netService.selectNet(vo));
		model.addAttribute("r", netService.selectReplyList(vo));
		
		return "reply";
	}
	
	@RequestMapping(value = "/insertReply")
	public String insertReply(HttpServletRequest request, Model model, ReplyVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
		if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 로그인 페이지로 이동 */
			return "redirect:/login";
		}
		netService.insertReply(vo);
		return "redirect:/reply?id=" + vo.getBoardId();
	}

	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback(Locale locale, Model model, HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		params.put("state", state);
		JSONObject tokenObject = userService.getNaverToken(params);
		Map<String, Object> tokenMap = new HashMap<>();
		for (Object keyObj : tokenObject.keySet()) {
			String key = (String) keyObj;
			tokenMap.put(key, tokenObject.get(key));
		}

		JSONObject userInfoObject = userService.getNaverUserProfile(tokenMap);
		Map<String, Object> userInfoMap = new HashMap<>();
		for (Object keyObj : userInfoObject.keySet()) {
			String key = (String) keyObj;
			Object value = userInfoObject.get(key);
			userInfoMap.put(key, userInfoObject.get(key));
		}

		JSONObject response = (JSONObject) userInfoMap.get("response");
		Map<String, Object> checkUserParams = new HashMap<>();
		Users user = new Users();
		for (Object keyObj : response.keySet()) {
			String key = (String) keyObj;
			Object value = response.get(key);
			checkUserParams.put(key, value);
			user.put(key, value);
		}
		checkUserParams.put("sns_type", "naver");
		user.setSns_type("naver");

		boolean checkUser = userService.checkUser(checkUserParams);
		if (!checkUser) { /* 처음 로그인이면 회원정보 저장하기 */
			int status = userService.insertUser(user);
			if (status == 1) {
				System.out.println("회원 정보 입력을 완료했습니다.");
			}
		}
		HttpSession session = request.getSession();         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
		session.setAttribute("loginUser", user);   	// 세션에 로그인 회원 정보 보관

		return "redirect:/list";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();   /* 세션 날림 */
		}

		return "redirect:/login";
	}
	
	@RequestMapping("/loginSuccess")
	public String loginComplete() {
		return "redirect:/list";
	}
}
