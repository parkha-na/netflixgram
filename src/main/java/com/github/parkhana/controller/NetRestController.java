package com.github.parkhana.controller;

import com.github.parkhana.service.NetService;
import com.github.parkhana.vo.RecommendedVo;
import com.github.parkhana.vo.Users;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NetRestController {

    @Autowired
    private NetService netService;

    @RequestMapping(value = "/board/recommended")
    public Map<String, Object> updateRecommended(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Map<String, Object> returnMap = new HashMap<>();
        if (session == null) {
            returnMap.put("status", false);
            returnMap.put("recommend_count", 0);
            returnMap.put("message", "권한 없음");
            return returnMap;
        }
        Users loginUser = (Users) session.getAttribute("loginUser");	/* 세션에 저장된 회원 조회 */
        if (loginUser == null) {	/* 세션에 회원 데이터가 없으면 홈으로 이동 */
            returnMap.put("status", false);
            returnMap.put("recommend_count", 0);
            returnMap.put("message", "권한 없음");
            return returnMap;
        }

        Map<String, Object> params = new HashMap<>();
        String boardId = request.getParameter("board_id");
        params.put("board_id", boardId);
        String userId = request.getParameter("user_id");
        params.put("user_id", userId);

        // 추천데이터가 있는지 확인한다.
        List<RecommendedVo> checkRecommended = netService.selectRecommended(params);
        if (checkRecommended.size() == 0) {
            // 추천데이터가 없다면 추천데이터를 입력한다.
            int insertStatus = netService.insertRecommended(params);
            if (insertStatus != 1) {
                returnMap.put("status", false);
                returnMap.put("recommend_count", 0);
                returnMap.put("message", "추천데이터 입력 실패");
                return returnMap;
            }
        } else {
            // 추천데이터가 있다면 추천상태를 변경한다. (추천 활성화, 비활성화)
            String checkedUseYn = checkRecommended.get(0).getUse_yn();
            String updateUseYn;
            if (StringUtils.equalsIgnoreCase("Y", checkedUseYn)) {
                updateUseYn = "N";
            } else {
                updateUseYn = "Y";
            }
            params.put("use_yn", updateUseYn);
            int updateStatus = netService.updateRecommended(params);
            if (updateStatus != 1) {
                returnMap.put("status", false);
                returnMap.put("recommend_count", 0);
                returnMap.put("message", "추천데이터 변경 실패");
                return returnMap;
            }
        }

        // 추천데이터가 있는지 확인한다.
        Map<String, Object> reCntParams = new HashMap<>();
        reCntParams.put("board_id", boardId);
        reCntParams.put("use_yn", "Y");
        int recommendedCnt = netService.selectRecommended(reCntParams).size();
        returnMap.put("status", true);
        returnMap.put("recommend_count", recommendedCnt);
        returnMap.put("message", "추천데이터 처리 성공");
        return returnMap;
    }
}
