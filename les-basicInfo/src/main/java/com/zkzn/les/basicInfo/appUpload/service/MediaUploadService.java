package com.zkzn.les.basicInfo.appUpload.service;


import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.appUpload.pojo.AppPackage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MediaUploadService
 * @Description TODO
 * @Author zhanglei
 * Date 2021/1/21 16:48
 * @Version 1.0
 **/
public interface MediaUploadService {

    String   register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) throws Exception;

    boolean checkChunk(String fileMd5, Integer chunk, Integer chunkSize) throws Exception;

    void uploadChunk(MultipartFile file, String fileMd5, Integer chunk) throws Exception;

    void mergeChunks(String url,String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) throws Exception;

    PageInfo findAppPackages(Map<String, Object> requestMap);

    void delAppPackageInfo(List<String> appPackageId) throws Exception;

    void saveAppPackagesInfo(AppPackage appPackage) throws Exception;

    Map<String, Object> findApp(Map<String, Object> requestMap) throws Exception;
}
