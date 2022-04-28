package com.github.parkhana.controller;

import com.github.parkhana.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + extension;
        fileService.fileUpload(file, newFileName);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/upload";
    }
}
