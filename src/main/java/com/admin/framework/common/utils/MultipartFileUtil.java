package com.admin.framework.common.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zsw
 * @date 2020-02-10 16:49
 */
public class MultipartFileUtil {

    public static boolean isEmpty(MultipartFile multipartFile){
        return  multipartFile == null || multipartFile.isEmpty();
    }

}
