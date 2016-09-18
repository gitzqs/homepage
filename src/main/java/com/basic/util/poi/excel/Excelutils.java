package com.basic.util.poi.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.util.String.StringUtil;
import com.basic.util.exception.BusinessLayerException;
import com.basic.util.poi.FileUtil;

/**
 * excel 操作
 * 
 * @author qiushi.zhou  
 * @date 2016年9月9日 上午10:16:44
 */
public class Excelutils {
	
	private static Logger logger = LoggerFactory.getLogger(Excelutils.class);
	private static HSSFWorkbook wb;
	
	/**
	 * excel导出
	 * 
	 * @param title ： excel标题名
	 * 		  heads : 表格头
	 * 		  params : 参数字段
	 * 	      dataList : 具体数据
	 * 	      imgPath : 图片地址（如果不需要，可为null）
	 *        id : 用户id，用来区分文件名
	 * @return void
	 */
	public static void exportExcel(HttpServletRequest request,HttpServletResponse response,String title,
			String heads,String params,List<Map<String,Object>> dataList,String imgPath,Long id){
		//临时文件存放地址
		String filePath = request.getSession().getServletContext().getRealPath("/")+"/tempFile/" ;
		String fileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") +"_"+id+".xls";
		//创建临时文件
		createExcel(title,title,StringUtil.stringToList(heads),StringUtil.stringToList(params),dataList,filePath+fileName,imgPath);
		
		//下载即导出
		FileUtil.downFile(response, filePath, fileName);
		
		//完成之后删除文件
		FileUtil.deleteFile(filePath+fileName);
	}
	
	/**
	 * 创建excel
	 * 
	 * @param 
	 * @return void
	 */
	private static void createExcel(String sheetName,String title,List<String> head,
			List<String> params,List<Map<String,Object>> dataList,String filePath,String imgPath){
		
		wb = new HSSFWorkbook();
		Map<String, CellStyle> cellStyle = setCellStyle(wb);
		//建立sheet对象
		HSSFSheet sheet = wb.createSheet(sheetName);
		
		
		//sheet中创建第一行,为标题行
		HSSFRow row1 = sheet.createRow(0);
		//创建单元格,赋值为标题
		HSSFCell cell = row1.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(cellStyle.get("TITLE_CSS"));
		
		//sheet第二行,创建表头
		HSSFRow row2 = sheet.createRow(1);
		if(head == null || head.size() == 0){
			throw new BusinessLayerException("excel创建失败：缺少表头！");
		}
		HSSFCell cell2 = null;
		for(int i=0; i<head.size(); i++){
			cell2 = row2.createCell(i);
			cell2.setCellValue(head.get(i));
			cell2.setCellStyle(cellStyle.get("HEADER_CSS"));
		}
		
		//sheet第三行及以后，创建数据
		if(params == null || params.size() == 0){
			throw new BusinessLayerException("excel创建失败：缺少参数");
		}
		if(dataList !=null && dataList.size() != 0){
			HSSFRow row3 = null;
			HSSFCell cell3 = null;
			for(int j=0; j<dataList.size(); j++){
				row3 = sheet.createRow(j+2);
				for(int k=0; k<params.size(); k++){
					cell3 = row3.createCell(k);
					cell3.setCellValue(dataList.get(j).get(params.get(k))==null 
							? "-" : dataList.get(j).get(params.get(k)).toString());
					cell3.setCellStyle(cellStyle.get("BODY_CSS"));
				}
			}
		}
		BufferedImage bufferImg = null;
		//添加图片
		if(!StringUtil.isEmpty(imgPath)){
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			try {
				bufferImg = ImageIO.read(new File(imgPath));
				ImageIO.write(bufferImg, StringUtil.getImgFormat(imgPath), byteArrayOut);
				
				// 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
				// anchor主要用于设置图片的属性
				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,
						(short) 0, dataList.size() +3, (short) 15, dataList.size()+23);
				// 插入图片
				patriarch.createPicture(anchor, wb.addPicture(
								byteArrayOut.toByteArray(),
								HSSFWorkbook.PICTURE_TYPE_JPEG));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//输出excel
		FileOutputStream outputStream = null ;
		try {
			outputStream = new FileOutputStream(filePath);
			wb.write(outputStream);
		} catch (FileNotFoundException e) {
			logger.error("create file failed:",e);
		}catch (IOException e) {
			logger.error("write to excel failed:",e);
		}finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("io close failed: ",e);
			}
		}
	}
	
	/**
	 * excel样式设置
	 * 
	 * @param 
	 * @return Map<String,CellStyle>
	 */
	private static Map<String, CellStyle> setCellStyle(Workbook wb) {

		Map<String, CellStyle> mapCellStyle = new HashMap<String, CellStyle>();
		CellStyle title_css = wb.createCellStyle();
		CellStyle header_css = wb.createCellStyle();
		CellStyle body_css = wb.createCellStyle();

		Font title_f = wb.createFont();
		Font header_f = wb.createFont();
		Font body_f = wb.createFont();

		title_f.setFontHeightInPoints((short) 15);
		title_f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		title_css.setFont(title_f);
		title_css.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		title_css.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中

		header_f.setFontHeightInPoints((short) 10);
		header_f.setColor(IndexedColors.BLACK.getIndex());
		header_f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		header_css.setFont(header_f);
		header_css.setBorderLeft(CellStyle.BORDER_THIN);
		header_css.setBorderRight(CellStyle.BORDER_THIN);
		header_css.setBorderTop(CellStyle.BORDER_THIN);
		header_css.setBorderBottom(CellStyle.BORDER_THIN);
		header_css.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		header_css.setFillPattern(CellStyle.SOLID_FOREGROUND);
		header_css.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		header_css.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中

		body_f.setFontHeightInPoints((short) 10);
		body_f.setColor(IndexedColors.BLACK.getIndex());

		body_css.setFont(body_f);
		body_css.setBorderLeft(CellStyle.BORDER_THIN);
		body_css.setBorderRight(CellStyle.BORDER_THIN);
		body_css.setBorderTop(CellStyle.BORDER_THIN);
		body_css.setBorderBottom(CellStyle.BORDER_THIN);

		mapCellStyle.put("TITLE_CSS", title_css);
		mapCellStyle.put("HEADER_CSS", header_css);
		mapCellStyle.put("BODY_CSS", body_css);

		return mapCellStyle;
	}
	
	public static void main(String args[]){
//		String sheetName = "测试专用";
//		String title = "测试";
//		List<String> head = new ArrayList<String>();
//		head.add("ID");
//		head.add("名字");
//		head.add("性别");
//		List<String> params = new ArrayList<String>();
//		params.add("id");
//		params.add("name");
//		params.add("sex");
//		
//		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
//		for(int i=0; i<10; i++){
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("id", i);
//			map.put("name", "张三"+i);
//			map.put("sex", "男"+i);
//			dataList.add(map);
//		}
//		String filePath = "D:/test.xls";
//		
//		new Excelutils().createExcel(sheetName, title, head, params, dataList, filePath, "F:/logo1.png");
		
		
	}

}	
