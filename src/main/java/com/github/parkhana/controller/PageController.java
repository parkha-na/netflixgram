package com.github.parkhana.controller;

import com.github.parkhana.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @Autowired
    BoardService bgService;

    @RequestMapping("/")
    public String index(HttpServletRequest httpServletRequest, Model model) {

        return "index";
    }
}
