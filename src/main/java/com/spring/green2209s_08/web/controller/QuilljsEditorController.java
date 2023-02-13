package com.spring.green2209s_08.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Slf4j
@Controller
public class QuilljsEditorController {

    private final String FILE_URL = "D:\\green2209s-08-files\\vendor\\board-image\\temp\\";

    @PostMapping("/common/vendor/image/upload")
    public ResponseEntity<FileStatusResponse> embedTempImage(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String savedFilename = UUID.randomUUID().toString();
        String extension = getExtension(originalFilename);

        file.transferTo(new File(FILE_URL + savedFilename + extension));
        FileStatusResponse statusResponse = new FileStatusResponse(HttpStatus.OK.toString(), "임시 이미지 저장 완료", "TRUE", (savedFilename + extension));
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    private static String getExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.indexOf("."));
    }

    @ResponseBody
    @GetMapping("/common/vendor/image/display/{imageName}")
    public ResponseEntity<byte[]> displayVendorImage(@PathVariable String imageName) throws IOException {
        File file = new File(FILE_URL + imageName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", Files.probeContentType(file.toPath()));

        ResponseEntity<byte[]> statusResponse = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

        return statusResponse;
    }

}
