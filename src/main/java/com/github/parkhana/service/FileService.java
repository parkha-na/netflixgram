package com.github.parkhana.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    // application.properties 에 app.upload.dir을 정의하고, 없는 경우에 default 값으로 user.home (System에 종속적인)
    @Value("${app.upload.jhjeon:${user.home}}")
    private String uploadDirJhjeon;

    @Value("${app.upload.hana:${user.home}}")
    private String uploadDirHana;

    @Value("${app.upload.dir}")
    private String uploadDirTarget;


    public void fileUpload(MultipartFile multipartFile, String newFileName) {

        String uploadDir = getUploadDirPath();

        // File.seperator 는 OS종속적이다.
        // Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제한다
        Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(newFileName));
        try {
            // inputStream을 가져와서
            // copyOfLocation (저장위치)로 파일을 쓴다.
            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
            Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUploadDirPath() {

        String uploadDir = "";
        if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(uploadDirTarget, "jhjeon")) {
            uploadDir = uploadDirJhjeon;
        } else {
            uploadDir = uploadDirHana;
        }
        return uploadDir;
    }
}
