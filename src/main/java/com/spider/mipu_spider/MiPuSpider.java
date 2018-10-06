package com.spider.mipu_spider;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.spider.entity.IP;
import com.spider.jdbc.MiPuJDBC;
import com.spider.utils.DownloadFileUtil;
import com.spider.utils.ImageRecognitionUtil;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class MiPuSpider extends BreadthCrawler {

	public MiPuSpider(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		
	}

	public void visit(Page page, CrawlDatums next) {
		// 创建一个存放ip的集合
		List<IP> list = new ArrayList<IP>();
		// 获取到ip标签集合
		Elements ipElements = page.select("tbody>tr");
		for (Element tr : ipElements) {

			String ipAddress = tr.select("tr>td.tbl-proxy-ip").text().trim(); // ip地址
			String ipPort = getIpPort(tr); // ip端口号
			String ipType = tr.select("tr>td.tbl-proxy-type").text().trim(); // ip类型
			String ipVerifyTime = tr.select("tr>td.tbl-proxy-checkdtime").text().trim(); // ip验证时间
			String ipLocation = tr.select("tr>td.tbl-proxy-country").text().trim(); // ip真实地址

			// 将ip添加到list集合
			IP ip = new IP(ipAddress, ipPort, ipType, ipLocation, ipVerifyTime);
			list.add(ip);
		}

		// 将可用代理存到数据库
		try {
			checkProxyIp(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 检测ip是否可用 将可用代理存到数据库
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private void checkProxyIp(List<IP> list) throws Exception {

		for (IP ip : list) {

			String address = ip.getIpAddress();
			Integer port = Integer.valueOf(ip.getIpPort());

			// 设置代理IP、端口、协议
			HttpHost proxy = new HttpHost(address, port, "http");

			// 把代理设置到请求配置
			RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();

			// 实例化CloseableHttpClient对象
			CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

			// 访问目标地址
			HttpGet httpGet = new HttpGet("http://www.baidu.com/");

			// 设置User-Agent模拟浏览器请求
			httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
			httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpGet.setHeader("Accept-Encoding", "gzip, deflate");
			httpGet.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
			// 请求返回
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				// 获取返回状态码
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) { // 如果状态码为200时将代理插入到数据库
					MiPuJDBC.insertIP(ip);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		}

	}

	/**
	 * 获取端口号
	 * 
	 * @param tr
	 */
	private String getIpPort(Element tr) {
		String ipPort = "";
		// ip端口号图片链接
		String ipPortUrl = "https://proxy.mimvp.com/" + tr.select("tr>td.tbl-proxy-port>img").attr("src").toString();
		boolean status = DownloadFileUtil.download(ipPortUrl, "G:/images/", "code.png"); // 下载图片
		if (status) {
			ipPort = ImageRecognitionUtil.getCodeByBaiDu("G:/images/code.png"); // 端口号
			System.out.println("识别出的端口号：" + ipPort);
		}
		return ipPort;
	}

	public static void main(String[] args) throws Exception {
		
		List<String> urls = new ArrayList<String>();
		urls.add("https://proxy.mimvp.com/free.php");
		urls.add("https://proxy.mimvp.com/freesecret.php");
		urls.add("https://proxy.mimvp.com/freesole.php");
		for (String url : urls) {
			MiPuSpider mp = new MiPuSpider("crawler", true);
			mp.addSeed(url);
			mp.setThreads(1);
			mp.start(1);
		}
		
	}

}
