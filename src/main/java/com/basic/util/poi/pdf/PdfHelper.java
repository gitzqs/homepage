package com.basic.util.poi.pdf;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * pdf操作辅助类
 * 
 * @author qiushi.zhou  
 * @date 2016年9月7日 下午4:02:40
 */
public class PdfHelper {
	
	public static ITextRenderer getRender() throws DocumentException, IOException {
		 
        ITextRenderer render = new ITextRenderer();
 
        String path = "F:/";
        //添加字体，以支持中文
        render.getFontResolver().addFont(path + "ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.getFontResolver().addFont(path + "SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
 
        return render;
    }
 
    //获取要写入PDF的内容
    public static String getPdfContent(String ftlPath, String ftlName, Object o) throws  ParseException, IOException, TemplateException {
        return useTemplate(ftlPath, ftlName, o);
    }
 
    //使用freemarker得到html内容
    public static String useTemplate(String ftlPath, String ftlName, Object o) throws ParseException, IOException, TemplateException {
 
        String html = null;
 
        Template tpl = getFreemarkerConfig(ftlPath).getTemplate(ftlName);
        tpl.setEncoding("UTF-8");
 
        StringWriter writer = new StringWriter();
        tpl.process(o, writer);
        writer.flush();
        html = writer.toString();
        return html;
    }
 
    /**
     * 获取Freemarker配置
     * @param templatePath
     * @return
     * @throws IOException
     */
    private static Configuration getFreemarkerConfig(String templatePath) throws IOException {
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File(templatePath));
        config.setEncoding(Locale.CHINA, "utf-8");
        return config;
    }
     
    /**
     * 获取类路径
     * @return
     */
    public static String getPath(){
        return PdfHelper.class.getResource("").getPath().substring(1);
    }
}
