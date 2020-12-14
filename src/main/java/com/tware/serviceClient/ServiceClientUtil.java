package com.tware.serviceClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

@Component
public class ServiceClientUtil {
	
	
	 @Value("${socketconfig.socketPort}")
	 private String socketPort;
	 
	 @Value("${socketconfig.socketUrl}")
	 private String socketUrl;
	

	/**
 一、初始化儿童补贴，教师津贴审批数据
1.参数类型：JSon字符串
2.格式：{"startTime":"2020-01-01","endTime":"2020-12-31"}
3.成功返回值：{\"Code\":0,"Msg":"成功"}
4.失败返回值：{\"Code\":1,"Msg":"失败"}

	 **/

	/**
	 * 统一调用服务接口
	 * @param param
	 * @return
	 */
	public  String csExtend(String param) {
		String jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-ReturnIsNULL"));
		Socket socket = null;
		OutputStream os = null;
		PrintWriter pw = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			String mqsUrl =socketUrl;
			String mqsPort = socketPort;
			//1.创建客户端Socket，指定服务器地址和端口
			socket = new Socket(mqsUrl, new Integer(mqsPort.trim()));
			//2.获取输出流，向服务器端发送信息
			os = socket.getOutputStream();//字节输出流
			pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"),true);//将输出流包装为打印流
			pw.write(param);
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			//3.获取输入流，并读取服务器端的响应信息
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String info = null;
			while((info=br.readLine())!=null) {
				jsonResult =info; 
			}
			
		} catch (ConnectException e) {
			e.printStackTrace();
			jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-ConnectException.ServiceClientUtil"));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-Exception.ServiceClientUtil"));
		} finally {
			//关闭资源
			try {
				if(br!=null)
					br.close();
				if(is!=null)
					is.close();
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				jsonResult = ""; //ResultFormat.jsonStrFail(PropertiesMsg.getProperty("custom-IOException.ServiceClientUtil"));
			}
		}
		return jsonResult;
	}

}
