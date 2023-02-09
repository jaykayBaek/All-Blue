//package com.spring.green2209s_08.web.service;
//
//import com.spring.green2209s_08.web.domain.ImageFile;
//import com.spring.green2209s_08.web.domain.Item;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Component
//public class ImageUploader {
//    @Value("${file.dir}")
//    private String fileDir;
//
//    public String getFullPath(String filename){
//        return fileDir + filename;
//    }
//
//    public List<ImageFile> storeImages(List<MultipartFile> multipartFiles) throws IOException {
//        List<ImageFile> storeFileResult = new ArrayList<>();
//        for (MultipartFile multipartFile : multipartFiles) {
//            if(!multipartFile.isEmpty()){
//                storeFileResult.add(storeImage(multipartFile));
//            }
//        }
//        return storeFileResult;
//    }
//
//    public ImageFile storeImage(MultipartFile multipartFile) throws IOException {
//        if(multipartFile.isEmpty()){
//            return null;
//        }
//
//        String originalImageName = multipartFile.getOriginalFilename();
//        String storedImageName = createStoredImageName(originalImageName);
//
//        multipartFile.transferTo(new File(getFullPath(storedImageName)));
//        return new ImageFile(originalImageName, storedImageName);
//    }
//
//    private String createStoredImageName(String originalFilename) {
//        String uuid = UUID.randomUUID().toString();
//        LocalDate localDate = LocalDate.now();
//        String ext = extractExt(originalFilename);
//        return uuid + "_" +localDate.toString() + "." + ext;
//    }
//
//    private String extractExt(String originalFilename) {
//        int index = originalFilename.lastIndexOf(".");
//        return originalFilename.substring(index + 1);
//    }
//
//}
