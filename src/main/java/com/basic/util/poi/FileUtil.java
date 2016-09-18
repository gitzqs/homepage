package com.basic.util.poi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import com.basic.util.exception.BaseRuntimeException;
/**
 * 文件操作
 * 
 * @author qiushi.zhou  
 * @date 2016年9月7日 下午2:28:46
 */
public class FileUtil {
	
	/**
	 * 下载
	 * 
	 * @param 
	 * @return void
	 */
	public static void downFile(HttpServletResponse response, String path,String filename) {
		InputStream ins = null;
		BufferedInputStream bins = null;
		OutputStream outs = null;
		BufferedOutputStream bouts = null;
		try {
			File file = new File(path+filename);
			if (file.exists()) {
				ins = new FileInputStream(path);
				bins = new BufferedInputStream(ins);// 放到缓冲流里面
				outs = response.getOutputStream();// 获取文件输出IO流
				bouts = new BufferedOutputStream(outs);
				response.setContentType("application/x-download");// 设置response内容的类型
				response.setHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(filename, "UTF-8"));// 设置头部信息
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				// 开始向网络传输文件流
				while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
					bouts.write(buffer, 0, bytesRead);
				}
				bouts.flush();// 这里一定要调用flush()方法
				
			}
			else {
				throw new BaseRuntimeException("文件下载失败!");
			}
		}catch (IOException e) {
			throw new BaseRuntimeException("文件下载异常:",e);
		}finally{
			try {
				ins.close();
				bins.close();
				outs.close();
				bouts.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 删除
	 * 
	 * @param 
	 * @return void
	 */
	public static void deleteFile(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			file.delete();
		}
	}
}
