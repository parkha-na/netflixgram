package com.github.parkhana.controller;

import com.github.parkhana.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestAPIController {

    @Autowired
    BoardService bgService;

    @RequestMapping("/board")
    public Map<String, Object> dndBoardGameList(HttpServletRequest httpServletRequest, Model model) {

        Map<String, Object> returnObj = new HashMap<>();

        int page = 1;
        int len = 10;

        String pageStr = httpServletRequest.getParameter("page");
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            page = 1;
        }

        returnObj.put("status", true);
        returnObj.put("list", bgService.selectBoardListToDB(page, len));

        return returnObj;
    }

    @RequestMapping("/board/add")
    public Map<String, Object> dndBoardGameAdd(HttpServletRequest httpServletRequest, Model model) {

        Map<String, Object> returnObj = new HashMap<>();

        returnObj.put("page", "dndBoardGameAdd");

        return returnObj;
    }

    @RequestMapping("/board/modify")
    public Map<String, Object> dndBoardGameUpdate(HttpServletRequest httpServletRequest, Model model) {

        Map<String, Object> returnObj = new HashMap<>();

        returnObj.put("page", "dndBoardGameUpdate");

        return returnObj;
    }

    @RequestMapping("/board/delete")
    public Map<String, Object> dndBoardGameDelete(HttpServletRequest httpServletRequest, Model model) {

        Map<String, Object> returnObj = new HashMap<>();

        returnObj.put("page", "dndBoardGameDelete");

        return returnObj;
    }
}
