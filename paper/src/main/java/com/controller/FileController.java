package com.controller;

import com.controller.base.BaseController;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;


@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public boolean upload(HttpServletRequest request) throws Exception{
		System.out.println("当前服务器路径为：" + System.getProperty("catalina.home"));
		String sele = request.getParameter("inTest");
		System.out.println(sele);
//		// 获取图片完整名称
//		String fileStr = multipartFile.getOriginalFilename();
//		System.out.println("文件旧名称：" + fileStr);
//		/**
//		 * 2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名。
//		 * “fileStr.substring(fileStr.lastIndexOf("."))”就是获取文件的后缀名。
//		 */
//		String newFileName = UUID.randomUUID().toString() +
//				fileStr.substring(fileStr.lastIndexOf("."));
//		System.out.println("文件新名称：" + newFileName);
//		// 保存在硬盘
//		multipartFile.transferTo(new File(System.getProperty("catalina.home") + "/work/" + newFileName));
		return true;
	}

	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//给要下载的文件起一个名字
		String downName = new String("下载内容.docx".getBytes("utf-8"), "iso8859-1");
		headers.setContentDispositionFormData("attachment", downName);
		//G盘中的0.jpg是待下载的图片。这里讲待下载的图片放在G盘，也可以放在项目目录下
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File("E:/paper/轮科研论文，谁主沉浮.docx")),
				headers, HttpStatus.CREATED);
	}
}
