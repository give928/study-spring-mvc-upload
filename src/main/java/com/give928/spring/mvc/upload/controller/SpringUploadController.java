package com.give928.spring.mvc.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file1,
                           @RequestParam MultipartFile file2,
                           HttpServletRequest request) throws IOException {
        log.info("request={}", request);
        log.info("itemName={}", itemName);
        log.info("multipartFile1={}", file1);
        log.info("multipartFile2={}", file2);
        if (!file1.isEmpty()) {
            String fullPath = fileDir + file1.getOriginalFilename();
            log.info("파일1 저장 fullPath={}", fullPath);
            file1.transferTo(new File(fullPath));
        }
        if (!file2.isEmpty()) {
            String fullPath = fileDir + file2.getOriginalFilename();
            log.info("파일2 저장 fullPath={}", fullPath);
            file2.transferTo(new File(fullPath));
        }
        return "upload-form";
    }
}
