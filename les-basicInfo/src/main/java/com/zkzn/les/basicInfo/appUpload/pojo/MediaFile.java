package com.zkzn.les.basicInfo.appUpload.pojo;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/***
 * @Discription: 文件上传类
 * @Author: zhanglei on 2021/1/21 18:03
 */
@Data
@ToString
public class MediaFile {
    @Id
    //文件id
    private String fileId;
    //文件名称
    private String fileName;
    //文件原始名称
    private String fileOriginalName;
    //文件路径
    private String filePath;
    //文件url
    private String fileUrl;
    //文件类型
    private String fileType;
    //mimetype
    private String mimeType;
    //文件大小
    private Long fileSize;
    //文件状态
    private  int fileStatus;
    //上传时间
    private Date uploadTime;
    //处理状态
    private int processStatus;
    //tag标签用于查询
    private String tag;

}
