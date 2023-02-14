package com.spring.green2209s_08.web.utility;

import com.spring.green2209s_08.web.constants.UrlConst;
import com.spring.green2209s_08.web.controller.vendor.VendorInventoryRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUpload {
    /* --- 파일 저장시키기(대표이미지, 상세이미지들) --- */

    public String thumbnailImageUpload(MultipartFile multipartFile) throws IOException {
        String folderCreatedPath = createFolderPath();
        String getFolderPath = getFolderPath(folderCreatedPath, UrlConst.THUMBNAIL_URL);

        return createImage(getFolderPath, multipartFile);
    }

    public List<String> extraImagesUpload(List<MultipartFile> extra) throws IOException {
        String folderCreatedPath = createFolderPath();
        String getFolderPath = getFolderPath(folderCreatedPath, UrlConst.EXTRA_URL);
        List<String> result = new ArrayList<>();
        for (MultipartFile multipartFile : extra) {
            String image = createImage(getFolderPath, multipartFile);
            result.add(image);
        }
        return result;
    }

    private String createImage(String getFolderPath, MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String savedFilename = UUID.randomUUID().toString();
        String extension = getExtension(originalFilename);
        String filePath = Paths.get(getFolderPath, (savedFilename+extension)).toString();
        multipartFile.transferTo(new File(filePath));
        return savedFilename + extension;
    }

    private static String getFolderPath(String folderCreatedPath, String urlConst) {
        String folderPath = Paths.get(urlConst, folderCreatedPath).toString();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderPath;
    }

    private String createFolderPath() {
        LocalDate currentDate = LocalDate.now();
        String yyyy = String.valueOf(currentDate.getYear());
        String MM = String.valueOf(currentDate.getMonthValue());
        String dd = String.valueOf(currentDate.getDayOfMonth());

        return yyyy+"\\"+MM+"\\"+dd+"\\";
    }

    private String getExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.indexOf("."));
    }


}