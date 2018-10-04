package com.spider.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 * Title: JsonParseUtil
 * </p>
 * <p>
 * Description: json格式数据解析器
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月4日 下午4:58:59
 */
public class JsonParseUtil {

	/**
	 * 根据json数组转换成list集合数据
	 * 
	 * @return
	 */
	public static List<String> getStringList(String jsonString) {
		List<String> list = new ArrayList<String>();
		JSONArray parseArray = JSONArray.parseArray(jsonString);
		for (Object object : parseArray) {
			String str = object.toString();
			String keyWord = str.substring(str.indexOf(",\"Key\":\"") + 8, str.length() - 2);
			list.add("\"" + keyWord + "\"");
		}
		return list;
	}

}
