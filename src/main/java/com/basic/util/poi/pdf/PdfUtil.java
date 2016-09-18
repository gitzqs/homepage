package com.basic.util.poi.pdf;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class PdfUtil {
	
	  public static void main(String[] args) {
	        try {
	        	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        	Map<String,Object> params = new HashMap<String,Object>();
	        	params.put("A", "A1");
	        	params.put("B", "B1");
	        	params.put("C", "C1");
	        	params.put("D", "D1");
	        	Map<String,Object> params2 = new HashMap<String,Object>();
	        	params2.put("A", "A12");
	        	params2.put("B", "B12");
	        	params2.put("C", "C12");
	        	params2.put("D", "D12");
	        	list.add(params);
	        	list.add(params2);
	        	
	        	Map<Object, Object> o=new HashMap<Object, Object>();
	            o.put("name", "机器猫");
	            o.put("order", list);
	            String path="F:/";
	             
	            generateToFile(path, "tpl.ftl",path, o, "D:\\xdemo.pdf");
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	    }
	  
	/**
	 * 生成pdf到文件
	 * 
	 * @param 
	 * @return void
	 */
	public static void generateToFile(String ftlPath,String ftlName,String imageDiskPath,Object data,String outputFile) throws Exception {
        String html=PdfHelper.getPdfContent(ftlPath, ftlName, data);
        OutputStream out = null;
        ITextRenderer render = null;
        out = new FileOutputStream(outputFile);
        render = PdfHelper.getRender();
        render.setDocumentFromString(html);
        if(imageDiskPath!=null&&!imageDiskPath.equals("")){
            //html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
            render.getSharedContext().setBaseURL("file:/"+imageDiskPath);
        }
        render.layout();
        render.createPDF(out);
        render.finishPDF();
        render = null;
        out.close();
    }
}
