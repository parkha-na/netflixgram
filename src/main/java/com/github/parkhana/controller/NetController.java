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

import com.github.parkhana.vo.RecommendedVo;
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
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser != null) {	/* ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? */
			return "redirect:/list";
		}

		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();   /* ?????? ?????? */
		}

		return "redirect:/login";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, HttpServletRequest request, NetVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? */
			return "redirect:/login";
		}
		model.addAttribute("loginUser", loginUser);

		String page = StringUtils.defaultIfBlank((String) request.getParameter("page"), "1");
		String ch1 = StringUtils.defaultIfBlank((String) request.getParameter("ch1"), "");
		String ch2 = StringUtils.defaultIfBlank((String) request.getParameter("ch2"), "");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ch1", ch1);
		params.put("ch2", ch2);

		int pageNum = Integer.parseInt(page);
		int startPage = ((pageNum - 1) * 10);
		int endPage = (pageNum * 10) - 1;
		params.put("startPage", startPage);
		params.put("endPage", endPage);
		List<NetVo> boardList = netService.selectNetList(params);
		for (NetVo netVo : boardList) {
			NetVo resultVo = netService.TOTALREPLY(netVo);
			netVo.setReplyCnt(resultVo.getReplyCnt());
			Map<String, Object> reCntParams = new HashMap<>();
			reCntParams.put("board_id", netVo.getId());
			reCntParams.put("use_yn", 'Y');
			List<RecommendedVo> recommendedList = netService.selectRecommended(reCntParams);
			netVo.setRecommend(recommendedList.size());
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

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? */
			return "redirect:/login";
		}
		model.addAttribute("loginUser", loginUser);

		// id ??????????????? ?????? ?????? ????????? ????????? ????????????.
		String idStr = StringUtils.defaultIfBlank((String) request.getParameter("id"), "");
		if (StringUtils.isNotBlank(idStr)) {
			NetVo params = new NetVo();
			int id = Integer.parseInt(idStr);
			params.setId(id);
			NetVo netVo = netService.selectNet(params);
			model.addAttribute("netVo", netVo);
		}

		return "post";
	}

	@RequestMapping("/post/write")
	public String postWrite(HttpServletRequest request, NetVo vo) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ?????? */
			return "redirect:/login";
		}

		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("HHmmss");
		String timeStr = dayTime.format(time);
		Date now = new Date();
		vo.setUploaddate(now);

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

		int status = netService.insertNet(vo);
		if (status == 1) {
			logger.debug("????????? ?????? ??????");
		} else {
			logger.error("????????? ?????? ??????");
		}

		return "redirect:/";
	}

	@RequestMapping("/post/update")
	public String postUpdate(HttpServletRequest request, NetVo vo) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ?????? */
			return "redirect:/login";
		}

		int status = 0;
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("HHmmss");
		String timeStr = dayTime.format(time);
		Date now = new Date();
		vo.setUploaddate(now);

		MultipartFile imgFile = vo.getImgFile();
		if (imgFile != null) {
			String originalFilaName = imgFile.getOriginalFilename();
			if (originalFilaName.length() > 0) {
				String extension = originalFilaName.substring(imgFile.getOriginalFilename().lastIndexOf("."), originalFilaName.length());
				UUID uuid = UUID.randomUUID();
				String newFileName = uuid.toString() + extension;
				fileService.fileUpload(imgFile, newFileName);
				vo.setImg(newFileName);
				status = netService.updateNetWithImg(vo);
			} else {
				status = netService.updateNet(vo);
			}
		} else {
			status = netService.updateNet(vo);
		}

		if (status == 1) {
			logger.debug("????????? ?????? ??????");
		} else {
			logger.error("????????? ?????? ??????");
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
	public String deleteNet(HttpServletRequest request, NetVo netvo, ReplyVo replyvo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? */
			return "redirect:/login";
		}
		NetVo k = netService.selectNet(netvo);
		String delFile = k.getImg();
		String path = request.getSession().getServletContext().getRealPath("/net/files/");
		String del = path + delFile;
		File f = new File(del);
		f.delete(); // ?????? ??????

		netService.deleteReply(replyvo);
		netService.deleteNet(netvo);
		Map<String, Object> params = new HashMap<>();
		params.put("board_id", netvo.getId());
		netService.deleteRecommended(params);
		return "redirect:/";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(HttpServletRequest request, Model model, NetVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? */
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
		if (!checkUser) { /* ?????? ??????????????? ???????????? ???????????? */
			int status = userService.insertUser(user);
			if (status == 1) {
				System.out.println("?????? ?????? ????????? ??????????????????.");
			}
		}
		HttpSession session = request.getSession();         // ????????? ????????? ?????? ?????? ??????, ????????? ?????? ????????? ???????????? ??????
		session.setAttribute("loginUser", user);   	// ????????? ????????? ?????? ?????? ??????

		return "redirect:/list";
	}

	@RequestMapping(value = "/insertReply")
	public String insertReply(HttpServletRequest request, Model model, ReplyVo vo) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/login";
		}
		Users loginUser = (Users) session.getAttribute("loginUser");	/* ????????? ????????? ?????? ?????? */
		if (loginUser == null) {	/* ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? */
			return "redirect:/login";
		}
		netService.insertReply(vo);
		return "redirect:/reply?id=" + vo.getBoard_id();
	}

	@RequestMapping(value = "/deleteReply")
	public String deleteReply(HttpServletRequest request, ReplyVo vo) {
		netService.deleteReply(vo);
		return "redirect:/reply";
	}
}
