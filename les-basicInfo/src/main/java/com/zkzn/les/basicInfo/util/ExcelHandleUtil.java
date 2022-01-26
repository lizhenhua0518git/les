package com.zkzn.les.basicInfo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**.
 * 
 *
 * 功能描述：excel
 * @author wangzhou
 * 时间：2018年7月6日
 */
public final class ExcelHandleUtil {

	  
    private ExcelHandleUtil() {
        
    }
    
	/**.
	 * 
	 * 功能描述：
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param titleList
	 * @param dataContent
	 * @param filedNames
	 * @return
	 * @throws Exception
	 */
	public static  HSSFWorkbook exportExcel(List<String> titleList,List dataContent,List<String> filedNames) throws Exception{
		//创建工作簿对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建工作表
		HSSFSheet  sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(10);
		//创建excel标题
		createTitle(sheet,titleList,workbook);
		//写入内容值
		createContent(workbook,sheet,dataContent,filedNames);
		
		return workbook;
	}
	/**.
	 * 
	 * 功能描述：创建excel标题
	 * 作者：wangzhou
	 * 时间：2018年7月6日
	 * @param sheet
	 * @param titleList
	 */
	public static void createTitle(HSSFSheet  sheet,List<String> titleList,HSSFWorkbook workbook){
		HSSFRow  hssfRow = sheet.createRow(0);
		HSSFCellStyle headStyle = workbook.createCellStyle(); //表头
		HSSFFont headFont = workbook.createFont(); //表头字体
		headFont.setBold(true); //宽度
		headFont.setFontHeightInPoints((short)10); //字号
		headFont.setFontName("Arial Unicode MS");//设置字体
		headStyle.setFont(headFont);
		//设置
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		headStyle.setWrapText(true); //自动换行
		HSSFCell cell = null;
		for(int i=0;i<titleList.size();i++){
			cell = hssfRow.createCell(i, CellType.STRING);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titleList.get(i));
		}
	}
	/**.
	 * 
	 * 功能描述：写入内容
	 * 作者：wangzhou
	 * 时间：2018年7月6日
	 * @param sheet
	 * @param dataContent
	 * @param filedNames
	 * @throws Exception
	 */
	public static void createContent(HSSFWorkbook workbook,HSSFSheet  sheet,List<Object> dataContent,List<String> filedNames) throws Exception{
		HSSFRow  hssfRow = null;
		Object obj;
		Object fieldObj;
		HSSFCell cell;
		CellStyle cellStyle = workbook.createCellStyle();
		HSSFFont headFont = workbook.createFont(); //表头字体
		headFont.setFontHeightInPoints((short)10); //字号
		headFont.setFontName("Arial Unicode MS");//设置字体
		cellStyle.setFont(headFont);
		//设置
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		// CellStyle tempCellStyle =  workbook.createCellStyle(); 
		 CellStyle dateCellStyle =  workbook.createCellStyle();
		for(int i=0;i<dataContent.size();i++){
			hssfRow = sheet.createRow(i+1);
			obj = dataContent.get(i);
			if(obj instanceof Map){
				for(int k=0;k<filedNames.size();k++){
					fieldObj = ((Map) obj).get(filedNames.get(k));
					if(fieldObj!=null ){
						if(fieldObj instanceof String){
							cell = hssfRow.createCell(k,CellType.STRING);
							cell.setCellValue((String)fieldObj);
							sheet.setColumnWidth(k, getWidth((String)fieldObj));
							cell.setCellStyle(cellStyle);
						}else if(fieldObj instanceof Integer ||fieldObj instanceof BigInteger ){		
							
							 //hssfRow.createCell(k, CellType.NUMERIC).setCellValue((Integer) fieldObj);							 
							cell = hssfRow.createCell(k, CellType.NUMERIC);
							cell.setCellValue(Integer.valueOf(fieldObj.toString()));
							cell.setCellStyle(cellStyle);
 
						 }else if(fieldObj instanceof Date){
							
							 cell = hssfRow.createCell(k);
							 cell.setCellStyle(dateCellStyle(workbook,dateCellStyle));
							 sheet.setColumnWidth(k, 20*256);
							 cell.setCellValue((Date)fieldObj);
						 }else if(fieldObj instanceof Double ||fieldObj instanceof BigDecimal){
							 //hssfRow.createCell(k, CellType.FORMULA).setCellValue( (double) fieldObj)							 
							 cell = hssfRow.createCell(k, CellType.NUMERIC);
							 cell.setCellValue( (double)Double.valueOf(fieldObj.toString()));
							 cell.setCellStyle(cellStyle);
						 }else if(fieldObj instanceof Long ){
							 cell = hssfRow.createCell(k, CellType.NUMERIC);
							 cell.setCellValue( Long.valueOf(fieldObj.toString()));
							 cell.setCellStyle(cellStyle);
						 }
						
					}else{
						cell = hssfRow.createCell(k,CellType.STRING);
						cell.setCellValue("");
						cell.setCellStyle(cellStyle);
					}
					
				}
			}else{
				boolean flg = false;
				Field field=null;

				for(int k=0;k<filedNames.size();k++){
					Field[]  fileds = obj.getClass().getDeclaredFields();
					flg = false;
					s:for(Field tempfield:fileds){
						if(filedNames.get(k).equals(tempfield.getName())){
							flg = true;
							break s;
						}
					}
					if(flg){
						field = obj.getClass().getDeclaredField(filedNames.get(k));
					}else{
						field = obj.getClass().getSuperclass().getDeclaredField(filedNames.get(k));
					}
				
					//设置对象的访问权限，保证对private的属性的访问
					field.setAccessible(true);
					fieldObj = field.get(obj);
					if(fieldObj!=null){
						 if(fieldObj instanceof String){
							 cell = hssfRow.createCell(k,CellType.STRING);
							 cell.setCellValue((String)field.get(obj));
							 cell.setCellStyle(cellStyle);
						 }else if(fieldObj instanceof Integer){
							 cell = hssfRow.createCell(k, CellType.NUMERIC);
							 cell.setCellValue((Integer)field.get(obj));
							 cell.setCellStyle(cellStyle);
						 }else if(fieldObj instanceof Date){
							 cell = hssfRow.createCell(k);
							 cell.setCellStyle(dateCellStyle(workbook,dateCellStyle));
							 sheet.setColumnWidth(k, 20*256);
							 cell.setCellValue((Date)field.get(obj));
						 }else if(fieldObj instanceof Double){
							 cell=hssfRow.createCell(k, CellType.FORMULA);
							 cell.setCellValue( (double) fieldObj);
							 cell.setCellStyle(cellStyle);
						 }else if(fieldObj instanceof Long ){
							 cell = hssfRow.createCell(k, CellType.NUMERIC);
							 cell.setCellValue( Long.valueOf(fieldObj.toString()));
							 cell.setCellStyle(cellStyle);
						 }
					}else{
						cell = hssfRow.createCell(k,CellType.STRING);
						cell.setCellValue("");
						cell.setCellStyle(cellStyle);
					}
						
				}
			}
		}
	}
	/**.
	 * 
	 * 功能描述：获取日期格式
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param workbook
	 * @return
	 */
	public static CellStyle dateCellStyle(HSSFWorkbook workbook,CellStyle cellStyle){
		    
		CreationHelper creationHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(
					creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss")
				);
		HSSFFont headFont = workbook.createFont(); //表头字体
		headFont.setFontHeightInPoints((short)10); //字号
		headFont.setFontName("Arial Unicode MS");//设置字体
		cellStyle.setFont(headFont);
		//设置
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		return cellStyle;
	}
	/**.
	 * 
	 * 功能描述：获取日期格式
	 * 作者：wangzhou
	 * 时间：2018年10月29日
	 * @param sxssfwork
	 * @return
	 */
	public static CellStyle dateCellStyle(SXSSFWorkbook sxssfwork,CellStyle cellStyle){
		  cellStyle = sxssfwork.createCellStyle();
		CreationHelper creationHelper = sxssfwork.getCreationHelper();
		cellStyle.setDataFormat(
					creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss")
				);
		Font headFont = sxssfwork.createFont(); //表头字体
		headFont.setBold(true); //宽度
		headFont.setFontHeightInPoints((short)10); //字号
		headFont.setFontName("Arial Unicode MS");//设置字体
		cellStyle.setFont(headFont);
		//设置
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		return cellStyle;
	}
	
	/**
	 * 
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * 
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 *            
	 * @param dateFormat 需要日期时的日期格式 不需要时传入""
	 * 
	 * @param digitsNum 要保留的小数位数 
	 * 
	 * @return 读出的Excel中数据的内容
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 * 
	 */

	public static String[][] getData(MultipartFile file, int ignoreRows,String dateFormat,int digitsNum)

			throws FileNotFoundException, IOException,Exception {

		List<String[]> ballResult = new ArrayList<String[]>();// 数据

		int rowSize = 0;

		InputStream in =file.getInputStream();

		// 打开HSSFWorkbook
		Workbook wb = null;
		//POIFSFileSystem fs = new POIFSFileSystem(in);
		String fileName = file.getOriginalFilename();
		if(fileName.endsWith(".xlsx")){
			wb = new XSSFWorkbook(in);
		}else if(fileName.endsWith(".xls")){
			 wb = new HSSFWorkbook(in);
		}else {
            throw new Exception("文件格式不正确，不能导入！");
        }
	

		Cell cell = null;
		Sheet st = null;
		Row row = null;

		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {

			st = wb.getSheetAt(sheetIndex);

			// 第一行为标题，不取

			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

				row = st.getRow(rowIndex);

				if (row == null) {

					continue;

				}
				int tempRowSize = row.getLastCellNum();

				if (tempRowSize > rowSize) {

					rowSize = tempRowSize;

				}

				String[] values = new String[rowSize];

				Arrays.fill(values, "");

				boolean hasValue = false;

				for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {

					String value = "";

					cell = row.getCell(columnIndex);

					if (cell != null) {

						switch (cell.getCellTypeEnum()) {

						case STRING:

							value = cell.getStringCellValue();

							break;

						case NUMERIC:

							if (HSSFDateUtil.isCellDateFormatted(cell)) {

								Date date = cell.getDateCellValue();

								if (date != null) {

									value = new SimpleDateFormat(dateFormat).format(date);

								} else {

									value = "";

								}

							} else {
								DecimalFormat df = null;
								switch (digitsNum) {
								case 0:
									df = new DecimalFormat("#");// 不保留小数
									break;
                                case 1:
                                	df = new DecimalFormat("#.0"); // 保留一位小数
									break;
                                case 2:
                                	  df = new DecimalFormat("#.00"); // 保留两位小数
									break;
                                case 3:
                                	df = new DecimalFormat("#.000"); // 保留三位小数
									break;
                                case 4:
                                	df = new DecimalFormat("#.0000"); // 保留四位小数
									break;
								default:
									df = new DecimalFormat("#");// 默认不保留小数
									break;
								}
								
								String whatYourWant = df.format(cell.getNumericCellValue());
								value = whatYourWant;
								 

							}

							break;

						case FORMULA:

							// 导入时如果为公式生成的数据则无值

							if (!cell.getStringCellValue().equals("")) {

								value = cell.getStringCellValue();

							} else {

								value = cell.getNumericCellValue() + "";

							}

							break;

						case BLANK:

							break;

						case ERROR:

							value = "";

							break;

						case BOOLEAN:

							value = (cell.getBooleanCellValue() ? "Y": "N");

							break;

						default:

							value = "";

						}

					}

					if (columnIndex == 0 && value.trim().equals("")) {

						break;

					}

					values[columnIndex] = rightTrim(value);

					hasValue = true;

				}

				if (hasValue) {
					if (sheetIndex == 0) {
						// sheet表数据
						ballResult.add(values);
					}

				}

			}
		}
		wb.close();
		in.close();

		String[][] ballReturnArray = new String[ballResult.size()][rowSize];

		for (int i = 0; i < ballReturnArray.length; i++) {
			ballReturnArray[i] = (String[]) ballResult.get(i);

		}
       
		// 返回数据
		return ballReturnArray;

	}
	/**.
	 * 
	 * 功能描述：获取大数据excel对象
	 * 作者：wangzhou
	 * 时间：2018年10月29日
	 * @return
	 */
	public static SXSSFWorkbook createSxssfWork(){
		
		XSSFWorkbook xssfWb = new XSSFWorkbook();
		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWb);
		
		return sxssfWorkbook;
	}
	/**
	 * 
	 * 功能描述：SXSSFWorkbook数据导入
	 * 作者：wangzhou
	 * 时间：2018年10月29日
	 * @param list
	 * @param title
	 * @param filedNames
	 * @param sxssSheet
	 * @throws Exception
	 */
	public static void addDatatoRow(List list,List<String> title,List<String> filedNames,SXSSFSheet sxssSheet) throws Exception{
		int exportNum = list.size();
		SXSSFWorkbook sxssfwork = new SXSSFWorkbook();
		createTitle(sxssSheet,title);
		SXSSFRow row =null;
		Object obj = null;
		Object fieldObj;
		SXSSFCell cell;
		CellStyle cellStyle =sxssfwork.createCellStyle();
		for(int i=0;i<exportNum;i++){
			row = sxssSheet.createRow(i+1);
			obj = list.get(i);
			if(obj instanceof Map){
				for(int k=0;k<filedNames.size();k++){
					fieldObj = ((Map) obj).get(filedNames.get(k));
					if(fieldObj!=null ){
						if(fieldObj instanceof String){
							row.createCell(k,CellType.STRING).setCellValue((String)fieldObj);
						}else if(fieldObj instanceof Integer){
							row.createCell(k, CellType.NUMERIC).setCellValue((Integer) fieldObj);
						 }else if(fieldObj instanceof Date){
							 cell = row.createCell(k);
							 cell.setCellStyle(dateCellStyle(sxssfwork,cellStyle));
							 cell.setCellValue((Date)fieldObj);
						 }else if(fieldObj instanceof Double){
							 row.createCell(k, CellType.FORMULA).setCellValue( (double) fieldObj);
						 }
					}
					
				}
			}else{
				for(int k=0;k<filedNames.size();k++){
					Field field = obj.getClass().getDeclaredField(filedNames.get(k));
					//设置对象的访问权限，保证对private的属性的访问
					field.setAccessible(true);
					fieldObj = field.get(obj);
					if(fieldObj!=null){
						 if(fieldObj instanceof String){
							 row.createCell(k,CellType.STRING).setCellValue((String)field.get(obj));
						 }else if(fieldObj instanceof Integer){
							 row.createCell(k, CellType.NUMERIC).setCellValue((Integer)field.get(obj));
						 }else if(fieldObj instanceof Date){
							 cell = row.createCell(k);
							 cell.setCellStyle(dateCellStyle(sxssfwork,cellStyle));
							 cell.setCellValue((Date)field.get(obj));
						 }else if(fieldObj instanceof Double){
							 row.createCell(k, CellType.FORMULA).setCellValue( (double) fieldObj);
						 }
					}
						
				}
			}
		}
	}
	/**.
	 * 
	 * 功能描述：创建表头
	 * 作者：wangzhou
	 * 时间：2018年10月29日
	 * @param sxssSheet
	 * @param title
	 */
	public static void createTitle(SXSSFSheet sxssSheet,List<String> title){
		SXSSFRow row = sxssSheet.createRow(0);
		for(int i=0;i<title.size();i++){
			row.createCell(i, CellType.STRING).setCellValue(title.get(i));
		}
	}
	
	/**
	 * 
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 处理后的字符串
	 * 
	 */

	public static String rightTrim(String str) {

		if (str == null) {

			return "";

		}

		int length = str.length();

		for (int i = length - 1; i >= 0; i--) {

			if (str.charAt(i) != 0x20) {
				break;
			}

			length--;

		}

		return str.substring(0, length);

	}
	
	public static int getWidth(String str){
		int defualt = 10;
		if(str.length()>=20){
			defualt = 20;
		}else if(str.length()<=10){
			defualt = 10;
		}else{
			defualt = str.length();
		}
		return defualt*256;
	}
	
	public static Object getCellValue(Cell cell){
        if(cell == null){
            return null;
        }
        if(cell.getCellTypeEnum()==CellType._NONE){
            return null;
        }
        if(cell.getCellTypeEnum()==CellType.BLANK){
            return null;
        }
        if(cell.getCellTypeEnum()==CellType.STRING){
            return cell.getRichStringCellValue().getString();
        }
        if(cell.getCellTypeEnum()==CellType.NUMERIC){
            HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
            return  dataFormatter.formatCellValue(cell);
        }
        if(cell.getCellTypeEnum()==CellType.FORMULA){
            return cell.getCellFormula();
        }
        if(cell.getCellTypeEnum()==CellType.BOOLEAN){
            return cell.getBooleanCellValue();
        }
        return cell.getStringCellValue();
    }
}
