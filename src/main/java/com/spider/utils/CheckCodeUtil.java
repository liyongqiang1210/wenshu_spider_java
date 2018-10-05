package com.spider.utils;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * <p>
 * Title: CheckCodeUtil
 * </p>
 * <p>
 * Description: 验证码识别工具
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月5日 下午2:33:43
 */
public class CheckCodeUtil {

	public static void main(String[] args) {
		String checkCode = getCheckCode("G:/2.jpg");
		System.out.println(checkCode);
	}

	private static String getCheckCode(String filePath) {
		File imageFile = new File(filePath);
		ITesseract instance = new Tesseract();
		File tessDataFolder = LoadLibs.extractTessResources("tessdata");
		instance.setLanguage("eng");// 英文库识别数字比较准确
		// Set the tessdata path
		instance.setDatapath(tessDataFolder.getAbsolutePath());
		try {
			String result = instance.doOCR(imageFile);
			return result;
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return "";
	}
}
