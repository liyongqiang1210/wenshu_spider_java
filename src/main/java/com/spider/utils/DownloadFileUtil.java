package com.spider.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>
 * Title: DownloadFileUtil
 * </p>
 * <p>
 * Description: 文件下载工具类
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月6日 上午11:14:30
 */
public class DownloadFileUtil {

	/**
	 * 供外部调用的下载图片方法
	 * 
	 * @param downloadUrl
	 * @param saveFilePath
	 * @param fileName
	 * @return
	 */
	public static boolean download(String downloadUrl, String saveFilePath, String fileName) {

		return downloadPicture(downloadUrl, saveFilePath, fileName);
	}

	/**
	 * 下载图片
	 * 
	 * @param downloadUrl
	 * @param saveFilePath
	 * @param fileName
	 * @throws IOException
	 */
	private static boolean downloadPicture(String downloadUrl, String saveFilePath, String fileName) {

		try {
			URL url = new URL(downloadUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000); // 设置下载超时时间
			// 模拟浏览器请求
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			InputStream inputStream = conn.getInputStream(); // 得到输入流
			byte[] readInputStream = readInputStream(inputStream); // 获取自己的数组

			// 保存文件
			saveFile(saveFilePath, fileName, inputStream, readInputStream);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存文件到电脑
	 * 
	 * @param saveFilePath
	 * @param fileName
	 * @param inputStream
	 * @param readInputStream
	 * @throws IOException
	 */
	private static void saveFile(String saveFilePath, String fileName, InputStream inputStream, byte[] readInputStream)
			throws IOException {
		// 判断文件路劲是否存在不存在则创建
		File saveFile = new File(saveFilePath);
		if (!saveFile.exists()) {
			saveFile.mkdir();
		}
		// 将文件写到指定文件夹中
		File file = new File(saveFile + File.separator + fileName);
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(readInputStream);
		// 关闭流
		if (fo != null) {
			fo.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();

		return bos.toByteArray();
	}
}
