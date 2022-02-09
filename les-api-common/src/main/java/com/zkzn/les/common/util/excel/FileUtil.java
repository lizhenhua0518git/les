package com.zkzn.les.common.util.excel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Base64;
import java.util.Properties;
public final class FileUtil {

    private static String filePath;//文件保存路径
    
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static int maxFileSize;//文件最大的大小kb
  
    private static int maxMemSize;//最大内存大小

    private static String tempDir;//临时目录
    
    /**
     * 加载配置文件的文件路径
     */
    static {
        InputStream in = null;
        try {
            logger.info("加载上传文件参数开始");
            in = FileUtil.class.getResourceAsStream("/application.properties");
            Properties prop = new Properties();
            prop.load(in);
            FileUtil.filePath = prop.getProperty("image_store_prefix");
            FileUtil.maxFileSize = Integer.valueOf(prop.getProperty("file.maxSize"));
            FileUtil.maxMemSize = Integer.valueOf(prop.getProperty("maxMemSize"));
            FileUtil.tempDir = prop.getProperty("tempDir");
            logger.info("加载上传文件参数结束");
        } catch (Exception e) {
            logger.debug("文件工具类初始参数出错：" + e.getMessage());
        }finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                logger.debug("关闭输入流失败:" + e.getMessage());
            }

        }
    }
    private FileUtil() {
        
    }
    
    /**.
     * 功能描述: 根据路径创建文件目录
     * 时间:2018年5月24日 下午2:53:13
     * @param path
     * @return
     */
    public static boolean createDir(String path) {
        String basePath = System.getProperty("user.dir") + "\\src\\main\\java";
        path = path.replace('.', '\\');
        String destDirName = basePath + "\\" + path;
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录  
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    /**.
     * 
     * 功能描述：保存文件
     * 作者：wangzhou
     * 时间：2018年6月29日
     * @param file
     * @param fileName
     */
    public static void write(File file, String fileName) {
        if (!isOverSize(file)) {
            FileInputStream inStream = null;
            FileOutputStream outStream = null;
            try {
                inStream = new FileInputStream(file);
                outStream = new FileOutputStream(new File(FileUtil.filePath + fileName));
                byte[] btyeArry = new byte[1024];
                int read = 0;
                while ((read = inStream.read(btyeArry)) != -1) {
                    outStream.write(btyeArry, 0, read);
                }
            } catch (Exception e) {
                logger.debug("保存文件失败:" + e.getMessage());
            } finally {
                try {
                    if (inStream != null) {
                        inStream.close();
                    }
                } catch (IOException e) {
                    logger.debug("关闭输入流失败:" + e.getMessage());
                }
                try {
                    if (outStream != null) {
                        outStream.close();
                    }
                } catch (IOException e) {
                    logger.debug("关闭输出流失败:" + e.getMessage());
                }
            }
        } else {
            logger.debug("保存文件失败：文件超出大小" + FileUtil.maxFileSize);
        }
    }

    /**.
     * 
     * 功能描述：将文件读入到字节数组输出流中
     * 作者：wangzhou
     * 时间：2018年6月29日
     * @param filePath
     * @return
     */
    public static ByteArrayOutputStream readFile(String filePath) {
        ByteArrayOutputStream byteOutStream = null;
        if (exist(filePath)) {
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(filePath);
                byteOutStream = new ByteArrayOutputStream();
                byte[] byteArry = new byte[1024];
                int read;
                while ((read = inStream.read(byteArry)) != -1) {
                    byteOutStream.write(byteArry, 0, read);
                }
            } catch (Exception e) {
                logger.debug("读取文件失败！" + e.getMessage());

            } finally {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        logger.debug("关闭输入流失败:" + e.getMessage());
                    }
                }
                if (byteOutStream != null) {
                    try {
                        byteOutStream.close();
                    } catch (IOException e) {
                        logger.debug("关闭输出流失败:" + e.getMessage());
                    }
                }
            }
        } else {
            logger.debug("读取文件失败，文件不存在！");
        }
        return byteOutStream;
    }

    /**.
     * 
     * 功能描述：文件是否存在
     * 作者：wangzhou
     * 时间：2018年6月29日
     * @param filepath
     * @return
     */
    public static boolean exist(String filepath) {
        File file = new File(filepath);

        return file.exists();
    }

    /**.
     * 
     * 功能描述：将图片转换为base64
     * 作者：wangzhou
     * 时间:2018年6月11日
     * @param fileName
     * @return
     */
    public static String getImageStr(String fileName) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组  
        try {
            in = new FileInputStream(fileName);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.debug("关闭输入流失败:" + e.getMessage());
                }
            }
        }
        //对字节数组Base64编码  
       // BASE64Encoder encoder = new BASE64Encoder();
        return Base64.getEncoder().encodeToString(data);
                //encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }

    /**.
     * 功能描述：文件是否超出上传大小
     * 作者：wangzhou
     * 时间：2018年6月29日
     * @param file
     * @return
     */
    public static boolean isOverSize(File file) {
        return file.length() > FileUtil.maxFileSize;
    }

    public static String getDefualtPath() {
        return filePath;
    }

}
