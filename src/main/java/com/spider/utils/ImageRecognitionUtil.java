package com.spider.utils;

import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
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
 * Description: 图片识别工具
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月5日 下午2:33:43
 */
public class ImageRecognitionUtil {

	// 设置百度APPID/AK/SK
	private static final String APP_ID = "14352214";
	private static final String API_KEY = "bjuO72pRoTXTzyFK5bMEwWyk";
	private static final String SECRET_KEY = "P8r8H6ZufQoVwGfnKdYk7zc8Y1ZfZ2zX";

	/**
	 * 通过百度图片识别技术获取图片中内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getCodeByBaiDu(String filePath) {
		return imageRecognitionByBaidu(filePath);
	}

	/**
	 * 通过tees4j技术获取图片中内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getCodeByTees4j(String filePath) {
		return imageRecognitionByTees4j(filePath);
	}

	/**
	 * tees4j图片识别
	 * 
	 * @param filePath
	 * @return
	 */
	private static String imageRecognitionByTees4j(String filePath) {
		File imageFile = new File(filePath);
		ITesseract instance = new Tesseract();
		File tessDataFolder = LoadLibs.extractTessResources("tessdata");
		instance.setLanguage("eng");// 英文库识别数字比较准确
		instance.setDatapath(tessDataFolder.getAbsolutePath()); // 设置一个识别库文件地址
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
	private static String imageRecognitionByBaidu(String filePath) {

		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 调用接口
		JSONObject res = client.basicGeneral(filePath, new HashMap<String, String>());
		// 获取识别出来的验证码
		String words = res.get("words_result").toString();
		// 直接截取字符串
		String code = words.substring(words.indexOf(":\"") + 2, words.length() - 3);

		return code;
	}

	public static void main(String[] args) {
		String checkCode = imageRecognitionByTees4j("G:/2.jpg");
		System.out.println(checkCode);
		String checkCodeByBaidu = imageRecognitionByBaidu("G:/1.png");
		System.out.println(checkCodeByBaidu);
	}
}