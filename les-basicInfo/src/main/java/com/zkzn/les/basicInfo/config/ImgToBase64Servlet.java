package com.zkzn.les.basicInfo.config;

import com.alibaba.fastjson.JSONObject;
import com.zkzn.les.basicInfo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author luozhihong
 * 访问图片的servlet
 */
@WebServlet(urlPatterns="/imgToBase64Servlet")
public class ImgToBase64Servlet extends HttpServlet{

	private static final long serialVersionUID = 3640918487202003153L;
	
	private static final Logger logger = LoggerFactory.getLogger(ImgToBase64Servlet.class);

	private String filePath;//文件保存路径
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String imgStr = "";
		JSONObject object = new JSONObject();
		try{
			String fileName = request.getParameter("fileName");
			filePath = FileUtil.getDefualtPath();
			logger.debug("imgPath:"+filePath+fileName);
			File imgFile = new File(filePath+fileName);
			//设置返回的文件属性
			response.setContentType("image/jpeg");
			response.setContentLength((int) imgFile.length());
			//打开文件并输出
		    FileInputStream inputStream = new FileInputStream(imgFile);
			byte[] buffer = new byte[(int) imgFile.length()];
			int offset = 0;
			int numRead = 0;
			while (offset < buffer.length && (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
				offset += numRead;
			}

			if (offset != buffer.length) {
				throw new IOException("Could not completely read file "
						+ imgFile.getName());
			}
			inputStream.close();
			BASE64Encoder encoder = new BASE64Encoder();
			imgStr = encoder.encode(buffer);
			String img_path="data:image/jpeg;base64,"+imgStr;
			System.out.print("img_path==="+img_path+"\n");
			object.put("data", "data:image/jpg;base64," + imgStr);
			response.getWriter().write(object.toString());
		} catch (IOException e) {
		}catch(Exception e){
			logger.debug("getImg error:"+e.getMessage());
			response.getWriter().write("");
		}finally {
		}
	}

	public static String imgToBase64(File imgFile) throws IOException {
		BufferedImage read = ImageIO.read(imgFile);
		return imgBufferedToBase64String(read);

	}

	public static String imgToBase64(URL imgUrl) throws IOException {
		BufferedImage read = ImageIO.read(imgUrl);
		return imgBufferedToBase64String(read);

	}
	public static String imgBufferedToBase64String(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		return Base64Utils.encodeToString(bytes);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
