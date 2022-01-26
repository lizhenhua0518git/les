package com.zkzn.les.basicInfo.appUpload.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.zkzn.les.basicInfo.appUpload.dao.MediaFileDao;
import com.zkzn.les.basicInfo.appUpload.pojo.AppPackage;
import com.zkzn.les.basicInfo.appUpload.pojo.MediaFile;
import com.zkzn.les.basicInfo.appUpload.service.MediaUploadService;
import com.zkzn.les.basicInfo.util.UuidUtil;
import com.zkzn.les.common.util.page.PageUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/***
 * @Discription: app包管理上传服务
 * @Author: zhanglei on 2021/1/21 16:45
 * fileMd5 文件md5 表示文件全局唯一 由前端通过md加密算法计算而来
 */
@Service
public class MediaUploadServiceImpl implements MediaUploadService {

    //字符串连接器
    private final Joiner joiner = Joiner.on("/").skipNulls();
    //日志
    private final Logger LOGGER = LoggerFactory.getLogger(MediaUploadServiceImpl.class);

    @Autowired
    private MediaFileDao mediaFileDao;

    //TODO 文件存储的路径待定
    @Value("${les.app.package.uploadLocation}")
    String upload_location;

    //得到文件所属目录路径
    private String getFileFolderPath(String fileMd5) {
        return upload_location + joiner.join(new String[]{fileMd5.substring(0, 1), fileMd5.substring(1, 4), null});
    }

    //得到文件的路径
    private String getFilePath(String fileMd5, String fileExt) {
        return upload_location + joiner.join(new String[]{fileMd5.substring(0, 1), fileMd5.substring(1, 4)}).concat("/") + fileMd5 + "." + fileExt;
    }

    //得到块文件所属目录路径
    private String getChunkFileFolderPath(String fileMd5) {
        return upload_location + joiner.join(new String[]{fileMd5.substring(0, 1), fileMd5.substring(1, 4), null}) + "/chunk/";
    }

    /**
     * 文件上传前的注册，检查文件是否存在
     * 根据文件md5得到文件路径
     * 规则：
     * 一级目录：md5的第一个字符
     * 二级目录：md5的第二个字符
     * 三级目录：md5
     * 文件名：md5+文件扩展名
     *
     * @param fileMd5 文件md5值
     * @param fileExt 文件扩展名
     * @return 文件路径
     */
    public String  register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) throws Exception {

        LOGGER.info("注册参数：[{}]",fileMd5);
        //1  检查文件在磁盘上是否存在
        //文件所属目录的路径
        String fileFolderPath = this.getFileFolderPath(fileMd5);
        //文件的路径
        String filePath = this.getFilePath(fileMd5, fileExt);
        File file = new File(filePath);
        //文件是否存在
        boolean exists = file.exists();

        //文件不存在时作一些准备工作，检查文件所在目录是否存在，如果不存在则创建
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        //2 检查文件信息是否存在
        MediaFile fileByMd5 = this.mediaFileDao.findFileByMd5(fileMd5);
        if (exists && fileByMd5 !=null) {
           //返回md5
            return fileMd5;
        }
        return "";
    }

    /***
     * @Discription: 分块检查
     * @param fileMd5 文件的md5
     * @param chunk 块的下标
     * @param chunkSize 块的大小
     * @return boolean true 文件存在  false 文件不存在
     * @Author: zhanglei on 2021/1/21 17:14
     */
    public boolean checkChunk(String fileMd5, Integer chunk, Integer chunkSize) {
        LOGGER.info("分块检查：[{}]",fileMd5);

        //文件是否存在的flag true 表示文件存在，false 表示文件不存在
        boolean flag = false;
        //检查分块文件是否存在
        //得到分块文件的所在目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        //块文件
        File chunkFile = new File(chunkFileFolderPath + chunk);
        if (chunkFile.exists()) {
            //块文件存在
            flag = true;
        }
        return flag;
    }

    /***
     * @Discription: 文件上传
     * @param file
     * @param fileMd5
     * @param chunk
     * @return void
     * @Author: zhanglei on 2021/1/21 17:21
     */
    public void uploadChunk(MultipartFile file, String fileMd5, Integer chunk) throws Exception {
        LOGGER.info("分块上传：[{}]",fileMd5);
        //检查分块目录，如果不存在则要自动创建
        //得到分块目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        //得到分块文件路径
        String chunkFilePath = chunkFileFolderPath + chunk;
        File chunkFileFolder = new File(chunkFileFolderPath);
        //如果不存在则要自动创建
        if (!chunkFileFolder.exists()) {
            chunkFileFolder.mkdirs();
        }
        //得到上传文件的输入流
        LOGGER.info("file input:[{}]",file);
        try (InputStream inputStream = file.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(new File(chunkFilePath))) {
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException ioException) {
            //流自动关闭
            LOGGER.error("分片上传失败:[{}]", ioException.getMessage(), ioException);
            throw new Exception("app包分片上传失败");
        }
    }

    /***
     * @Discription: 合并文件
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return void
     * @Author: zhanglei on 2021/1/21 17:57
     */
    public void mergeChunks(String url,String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) throws Exception {
        //1、合并所有分块
        //得到分块文件的属目录
        LOGGER.info("分块合并：[{}],[{}]",url,fileMd5);
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        //分块文件列表
        File[] files = chunkFileFolder.listFiles();
        List<File> fileList = Arrays.asList(files);
        //创建一个合并文件
        String filePath = this.getFilePath(fileMd5, fileExt);
        File mergeFile = new File(filePath);
        //执行合并
        mergeFile = this.mergeFile(fileList, mergeFile);
        if (mergeFile == null) {
            //合并文件失败
            LOGGER.error("app包管理合并文件失败");
            throw new Exception("合并文件失败");
        }
        //2、校验文件的md5值是否和前端传入的md5一到
//        boolean checkFileMd5 = this.checkFileMd5(mergeFile, fileMd5);
//        if (!checkFileMd5) {
//            //校验文件失败
//            LOGGER.error("文件检验失败");
//            throw new Exception("校验文件失败");
//        }
        //3.保存文件
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId(fileMd5);
        mediaFile.setFileOriginalName(fileName);
        mediaFile.setFileName(Joiner.on(".").join(fileMd5, fileExt));
        //文件路径保存相对路径
        String filePath1 = joiner.join(new String[]{fileMd5.substring(0, 1), fileMd5.substring(1, 4)}).concat("/") + fileMd5 + "." + fileExt;
        mediaFile.setFilePath(filePath1);
        mediaFile.setFileSize(fileSize);
        mediaFile.setUploadTime(new Date());
        mediaFile.setMimeType(mimetype);
        mediaFile.setFileType(fileExt);
        //状态为上传成功 上传状态 1 表示上传成功 0 表示文件上传失败
        mediaFile.setFileStatus(1);
        mediaFile.setFileUrl(url.concat("/").concat("iles").concat("/app/").concat(filePath1));
        mediaFile.setProcessStatus(1);
        //保存文件到数据库
        mediaFileDao.saveMediaFile(mediaFile);
    }

    private String getServerIp() throws Exception{
        String SERVER_IP = null;
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                SERVER_IP = ip.getHostAddress();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                    SERVER_IP = ip.getHostAddress();
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            LOGGER.error("ip 地址获取失败");
            throw  new Exception("ip 地址获取失败");
        }
        return SERVER_IP;
    }

    /***
     * @Discription: 检验文件
     * @param mergeFile
     * @param md5
     * @return boolean
     * @Author: zhanglei on 2021/1/22 9:35
     */
    private boolean checkFileMd5(File mergeFile, String md5) {
        try {
            //创建文件输入流
            FileInputStream inputStream = new FileInputStream(mergeFile);
            //得到文件的md5
            String md5Hex = DigestUtils.md5Hex(inputStream);
            //和传入的md5比较
            if (md5.equalsIgnoreCase(md5Hex)) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("app包合并检验文件信息错误:[{}]", e.getMessage(), e);
            return false;
        }
        return false;
    }

    /**
     * 合并文件
     *
     * @param chunkFileList
     * @param mergeFile
     * @return
     */
    private File mergeFile(List<File> chunkFileList, File mergeFile) {
        try {
            //如果合并文件已存在则删除，否则创建新文件
            if (mergeFile.exists()) {
                mergeFile.delete();
            } else {
                //创建一个新文件
                mergeFile.createNewFile();
            }
            //对块文件进行排序
            Collections.sort(chunkFileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {
                        return 1;
                    }
                    return -1;
                }
            });
            //创建一个写对象
            RandomAccessFile rafWrite = new RandomAccessFile(mergeFile, "rw");
            byte[] b = new byte[1024];
            for (File chunkFile : chunkFileList) {
                RandomAccessFile rafRead = new RandomAccessFile(chunkFile, "r");
                int len = -1;
                while ((len = rafRead.read(b)) != -1) {
                    rafWrite.write(b, 0, len);
                }
                rafRead.close();
            }
            rafWrite.close();
            return mergeFile;
        } catch (IOException e) {
            LOGGER.error("app包合并异常:[{}]", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 分页查询app包升级
     * @param requestMap
     * @return
     */
    @Override
    public PageInfo findAppPackages(Map<String, Object> requestMap) {
        PageUtil.setPageParam(requestMap);
        if (requestMap.get("createTime") != null) {
            String createTime = String.valueOf(requestMap.get("createTime"));
            if (!"".equals(createTime) && createTime!= null) {
                String[] split = createTime.split(" - ");
                requestMap.putIfAbsent("startCreateTime",split[0]);
                requestMap.putIfAbsent("endCreateTime",split[1]);
            }
        }
        String wCode = null;
        if (requestMap.get("warehouseCode") !=null){
            wCode = String.valueOf(requestMap.get("warehouseCode"));
        }
        List<Map<String, Object>> qualityMaterials = mediaFileDao.findAppPackages(requestMap,wCode);

        PageInfo appPackages= new PageInfo<>(qualityMaterials);
        return appPackages;
    }

    /**
     * 删除app包信息 byid
     * @param appPackageId
     */
    @Override
    public void delAppPackageInfo(List<String> appPackageId) throws Exception {
       if (appPackageId.size() == 0) {
           throw new Exception("请求参数数据为空");
       }
       mediaFileDao.delAppPackageInfo(appPackageId);
    }

    /***
     * @Discription: app升级包信息保存
     * @param appPackage
     * @return void
     * @Author: zhanglei on 2021/1/24 18:14
     */
    @Override
    @Transactional
    public void saveAppPackagesInfo(AppPackage appPackage) throws Exception{
        //升级范围 资源id 不能为空，一个app包信息保存的前提就是有作用范围跟作用资源
        if (StringUtils.isEmpty(appPackage.getMediaId()) || StringUtils.isEmpty(appPackage.getWareHouseStr())) {
            throw new Exception("app包升级缺少参数，资源id 或 作用范围");
        }
        //创建主表id
        String uuid = UuidUtil.getUUID();
        appPackage.setId(uuid);
        appPackage.setCreateTime(new Date());
        mediaFileDao.saveAppPackage(appPackage);
        appPackage.setWareHouseId(Arrays.asList(appPackage.getWareHouseStr().split(",")));
        //字表信息保存
        mediaFileDao.sevaAppPackageWareHouse(uuid,appPackage.getWareHouseId());
    }

    /***
     * @Discription: app端app升级包信息查询
     * @param requestMap
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/1/24 19:41
     */
    @Override
    public Map<String, Object> findApp(Map<String, Object> requestMap) throws Exception {
        //isPad 0 手持机 1 平板 wareHouseCode 仓库编码必传
        if (requestMap.get("isPad") == null || StringUtils.isEmpty(String.valueOf(requestMap.get("wareHouseCode")))) {
           throw new Exception("app 获取参数异常：isPad 或者 wareHouseCode 为空");
        }
        int isPad = Integer.valueOf(requestMap.get("isPad").toString());
        String wareHouseCode = requestMap.get("wareHouseCode").toString();
        //根据isPad跟wareHouseCode 查询app信息
        Map<String,Object>  appInfo = mediaFileDao.findApp(isPad,wareHouseCode);
        return appInfo;
    }
}
