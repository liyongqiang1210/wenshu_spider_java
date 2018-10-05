package com.spider.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.json.JSONObject;
import org.openqa.selenium.remote.http.HttpRequest;

import com.alibaba.fastjson.util.IOUtils;
import com.baidu.aip.ocr.AipOcr;

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

	// 设置APPID/AK/SK
	public static final String APP_ID = "14352214";
	public static final String API_KEY = "bjuO72pRoTXTzyFK5bMEwWyk";
	public static final String SECRET_KEY = "P8r8H6ZufQoVwGfnKdYk7zc8Y1ZfZ2zX";

	/**
	 * tees4j验证码识别
	 * 
	 * @param filePath
	 * @return
	 */
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

	/**
	 * 百度图片识别
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getCheckCodeByBaidu(String filePath) {
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 调用接口
		JSONObject res = client.basicGeneral(filePath, new HashMap<String, String>());
		Object object = res.get("words_result");
		String string = object.toString();
		return string;
	}

	public static void main(String[] args) {
		String checkCode = getCheckCode("G:/2.jpg");
		System.out.println(checkCode);
		String checkCodeByBaidu = getCheckCodeByBaidu("G:/1.png");
		System.out.println(checkCodeByBaidu);
	}
}
