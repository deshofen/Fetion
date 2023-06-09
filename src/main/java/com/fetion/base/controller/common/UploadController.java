package com.fetion.base.controller.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fetion.base.bean.CodeMsg;
import com.fetion.base.bean.Result;
import com.fetion.base.util.StringUtil;

/**
 * 公用的上传类
 * @author 卞宇轩
 *
 */
@RequestMapping("/upload")
@Controller
public class UploadController {

	@Value("${byx.upload.photo.sufix}")
	private String uploadPhotoSufix;
	
	@Value("${byx.upload.photo.maxsize}")
	private long uploadPhotoMaxSize;
	
	@Value("${byx.upload.photo.path}")
	private String uploadPhotoPath;//图片保存位置
	
	@Value("${byx.upload.file.maxsize}")
	private long uploadFileMaxSize;
	
	@Value("${byx.upload.file.path}")
	private String uploadFilePath;//文件保存位置
	
	private Logger log = LoggerFactory.getLogger(UploadController.class);
	
	/**
	 * 图片统一上传类
	 * @param photo
	 * @return
	 */
	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> uploadPhoto(@RequestParam(name="photo",required=true)MultipartFile photo){
		//判断文件类型是否是图片
		String originalFilename = photo.getOriginalFilename();
		//获取文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
		if(!uploadPhotoSufix.contains(suffix.toLowerCase())){
			return Result.error(CodeMsg.UPLOAD_PHOTO_SUFFIX_ERROR);
		}
		if(photo.getSize()/1024 > uploadPhotoMaxSize){
			CodeMsg codeMsg = CodeMsg.UPLOAD_PHOTO_ERROR;
			codeMsg.setMsg("图片大小不能超过" + (uploadPhotoMaxSize/1024) + "M");
			return Result.error(codeMsg);
		}
		//准备保存文件
		File filePath = new File(uploadPhotoPath);
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		filePath = new File(uploadPhotoPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
		//判断当天日期的文件夹是否存在，若不存在，则创建
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffix;
		try {
			photo.transferTo(new File(uploadPhotoPath+"/"+filename));
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("图片上传成功，保存位置：" + uploadPhotoPath + filename);
		return Result.success(filename);
	}
	
	/**
	 * 文件统一上传类
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/upload_file",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> uploadFile(@RequestParam(name="file",required=true)MultipartFile file){
		//判断文件类型是否是图片
		String originalFilename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
//		if(!uploadPhotoSufix.contains(suffix.toLowerCase())){
//			return Result.error(CodeMsg.UPLOAD_PHOTO_SUFFIX_ERROR);
//		}
		if(file.getSize()/1024 > uploadFileMaxSize){
			CodeMsg codeMsg = CodeMsg.UPLOAD_PHOTO_ERROR;
			codeMsg.setMsg("文件大小不能超过" + (uploadFileMaxSize/1024) + "M");
			return Result.error(codeMsg);
		}
		//准备保存文件
		File filePath = new File(uploadFilePath);
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		filePath = new File(uploadFilePath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
		//判断当天日期的文件夹是否存在，若不存在，则创建
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffix;
		try {
			file.transferTo(new File(uploadFilePath+"/"+filename));
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("文件上传成功，保存位置：" + uploadFilePath + filename);
		return Result.success(filename);
	}
}
