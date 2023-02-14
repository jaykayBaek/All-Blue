package com.spring.green2209s_08.web.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageTagSrcExtract {

    public static List<String> getImageFilename(String content){
        Pattern pattern = Pattern.compile("<img[^>]+src=\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(content);

        List<String> arr = new ArrayList<>();

        while (matcher.find()) {
            String src = matcher.group(1);
            arr.add(src);
        }
        return arr;
    }

}
