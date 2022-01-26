package com.zkzn.les.basicInfo.appUpload.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.appUpload.pojo.AppPackage;
import com.zkzn.les.basicInfo.appUpload.service.MediaUploadService;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/***
 * @Discription: app包管理上传Controller
 * @Author: zhanglei on 2021/1/21 16:46
 */
@RestController
@RequestMapping("/media/upload")
public class MediaUploadController {
    //日志
    private Logger LOGGER = LoggerFactory.getLogger(MediaUploadController.class);
    @Autowired
    MediaUploadService mediaUploadService;
    /***
     * @Discription: 文件上传前的注册
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/21 17:46
     */
    @PostMapping("/register")
    public String register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        try {
            String md5File= mediaUploadService.register(fileMd5,fileName,fileSize,mimetype,fileExt);
            return Result.toJson(Ecode.SUCCESS,md5File);
        } catch(Exception e) {
           LOGGER.error("文件上传前注册失败:[{}]",e.getMessage(),e);
            return  Result.toJsonUseApp(Ecode.FAIL, false);
        }
    }

    /***
     * @Discription: 分片检查是否已经上传
     * @param fileMd5
     * @param chunk
     * @param chunkSize
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/21 17:47
     */
    @PostMapping("/checkChunk")
    public String  checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
        try {
           boolean flag =  mediaUploadService.checkChunk(fileMd5,chunk,chunkSize);
            return Result.toJson(Ecode.SUCCESS,flag);
        } catch(Exception e) {
            LOGGER.error("文件上传chunk检测失败:[{}]",e.getMessage(),e);
            return  Result.toJsonUseApp(Ecode.FAIL, false);
        }
    }

    /***
     * @Discription: 分片上传
     * @param file
     * @param fileMd5
     * @param chunk
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/21 17:47
     */
    @PostMapping("/uploadChunk")
    public String uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
        try {
            mediaUploadService.uploadChunk(file,fileMd5,chunk);
            return Result.toJson(Ecode.SUCCESS,true);
        } catch(Exception e) {
            LOGGER.error("文件上传合并失败:[{}]",e.getMessage(),e);
            return  Result.toJsonUseApp(Ecode.FAIL, false);
        }
    }
    /***
     * @Discription: 合并文件
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/21 17:53
     */
    @PostMapping("/mergeChunks")
    public String mergechunks(String url,String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        try {
            mediaUploadService.mergeChunks(url,fileMd5,fileName,fileSize, mimetype,fileExt);
            return Result.toJson(Ecode.SUCCESS,true);
        } catch(Exception e) {
            LOGGER.error("文件上传合并失败:[{}]",e.getMessage(),e);
            return  Result.toJsonUseApp(Ecode.FAIL, false);
        }
    }

    /**
     * 分页查询app包升级记录
     * @param requestMap
     * @return
     */
    @GetMapping("/findAppPackages")
    public String findAppPackages(@RequestParam Map<String,Object> requestMap) {
        PageInfo pageInfo = null;
        try {
           pageInfo =  mediaUploadService.findAppPackages(requestMap);
        } catch(Exception e) {
            LOGGER.error("app包升级记录查询信息错误：[{}]",e.getMessage(),e);
            return Result.toJson(Ecode.FAIL, "app升级包查询信息异常:"+e.getMessage());
        }
        return Result.toJson(Ecode.SUCCESS,pageInfo);
    }


    /***
     * @Discription: appPackage 删除 By Id
     * @param appPackageId
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/24 17:37
     */
    @PostMapping(value = "/delAppPackageInfo",produces="application/json;charset=UTF-8")
    public  String delAppPackageInfo(@RequestBody List<String> appPackageId) {
        try {
            mediaUploadService.delAppPackageInfo(appPackageId);
            return Result.toJson(Ecode.SUCCESS,true);
        } catch (Exception e) {
            LOGGER.error("app升级包删除失败:[{}]",e.getMessage(),e);
            return Result.toJson(Ecode.FAIL,false);
        }
    }

    /**
     * app升级包信息保存
     * @param appPackage
     * @param request
     * @return
     */
    @PostMapping(value="/saveAppPackagesInfo",produces="application/json;charset=UTF-8")
    public String saveAppPackagesInfo(@RequestBody AppPackage appPackage, HttpServletRequest request) {
        //创建者id
        appPackage.setCreaterName(SecurityUserUtil.getCurrentUserName(request)==null?"":SecurityUserUtil.getCurrentUserName(request));
        appPackage.setCreaterId(SecurityUserUtil.getCurrentUserId(request) == null?"":SecurityUserUtil.getCurrentUserId(request));
        try {
            mediaUploadService.saveAppPackagesInfo(appPackage);
            return Result.toJson(Ecode.SUCCESS,true);
        } catch (Exception e) {
            LOGGER.error("app升级包信息保存失败:[{}]",e.getMessage(),e);
            return Result.toJson(Ecode.FAIL,false);
        }
    }

    /***
     * @Discription: app 端 app升级包信息查询
     * @param requestMap
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/24 19:36
     */
    @GetMapping("/findApp")
    public String findApp (@RequestParam Map<String,Object> requestMap) {
        try {
            Map<String,Object> appInfo = mediaUploadService.findApp(requestMap);
            return Result.toJsonUseApp(Ecode.SUCCESS,appInfo);
        } catch (Exception e) {
            LOGGER.error("app端app升级包信息查询错误:[{}]",e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL,false);
        }
    }
}
