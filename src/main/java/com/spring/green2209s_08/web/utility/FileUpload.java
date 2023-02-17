package com.spring.green2209s_08.web.utility;

import com.spring.green2209s_08.web.constants.UrlConst;
import com.spring.green2209s_08.web.domain.ItemImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class FileUpload {
    private final Pattern pattern = Pattern.compile("<img\\s+src=\"([^\"]+)\"");

    public ItemImage thumbnailImageUpload(MultipartFile multipartFile) throws IOException {
//        String folderCreatedPath = createFolderPath();
//        String getFolderPath = getFolderPath(UrlConst.THUMBNAIL_URL, folderCreatedPath);
        String imageName = createImage(UrlConst.THUMBNAIL_URL, multipartFile);
        ItemImage itemImage = ItemImage.builder()
                .thumbnailImage(true)
                .savedImageName(imageName)
                .originalImageName(multipartFile.getOriginalFilename())
                .fileSavedPath(UrlConst.THUMBNAIL_URL)
                .build();
        return itemImage;
    }

    public List<ItemImage> extraImagesUpload(List<MultipartFile> extra) throws IOException {
//        String folderCreatedPath = createFolderPath();
//        String getFolderPath = getFolderPath(UrlConst.EXTRA_URL, folderCreatedPath);
        List<ItemImage> result = new ArrayList<>();
        for (MultipartFile multipartFile : extra) {
            String imageName = createImage(UrlConst.EXTRA_URL, multipartFile);
            ItemImage itemImage = ItemImage.builder()
                    .thumbnailImage(false)
                    .savedImageName(imageName)
                    .originalImageName(multipartFile.getOriginalFilename())
                    .fileSavedPath(UrlConst.EXTRA_URL)
                    .build();
            result.add(itemImage);
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
    private String getFolderPath(String urlConst, String folderCreatedPath) {
        String folderPath = Paths.get(urlConst, folderCreatedPath).toString();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderPath+"\\";
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

    public void copyImageToChangedPath(String content) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            String matchSource = matcher.group(1);
            String source = matchSource.substring(matchSource.lastIndexOf("/")+1);

            // 복사할 파일 경로 + 파일
            String sourcePath = UrlConst.IMAGE_TEMP_DISPLAY_URL;

//            String createFolderPath = createFolderPath();
//            String folderPath = getFolderPath(UrlConst.IMAGE_DISPLAY_URL, createFolderPath);
            // 옮겨갈 파일 경로 + 파일
            File sourceFile = new File(sourcePath + source);
            File targetFile = new File(UrlConst.IMAGE_DISPLAY_URL + source);
            
            // 파일 경로 이동 및 임시 이미지 삭제
            sourceFile.renameTo(targetFile);
            File deleteFile = new File(sourcePath + source);
            deleteFile.delete();

        }
    }

    public String changeImagePathInContent(String content) {
        String oldPath = "http://localhost:9090/green2209s_08/common/vendor/image/display/";
        Pattern pattern = Pattern.compile("(<img[^>]+src\\s*=\\s*[\"'])(.*?)" + Pattern.quote(oldPath) + "(.*?)([\"'][^>]*>)");
        Matcher matcher = pattern.matcher(content);
        String changeSrc = "/green2209s_08/images/board/";

        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            String newSrc = matcher.group(1) + matcher.group(2) + changeSrc + matcher.group(3) + matcher.group(4);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(newSrc));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}