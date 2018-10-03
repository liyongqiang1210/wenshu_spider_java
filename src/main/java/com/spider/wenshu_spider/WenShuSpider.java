package com.spider.wenshu_spider;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cn.edu.hfut.dmic.webcollector.crawldb.DBManager;
import cn.edu.hfut.dmic.webcollector.crawler.Crawler;
import cn.edu.hfut.dmic.webcollector.fetcher.Executor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BerkeleyDBManager;

/**
 * <p>
 * Title: WenShuSpider
 * </p>
 * <p>
 * Description: 中国裁判文书网爬虫
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月3日 上午8:26:22
 */
public class WenShuSpider implements Executor {

	public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
		// 设置代理IP
		ChromeOptions options = new ChromeOptions();
		// Proxy proxy = new Proxy();
		// proxy.setHttpProxy("61.135.217.7:80222");
		// 创建chrome浏览器驱动
		WebDriver driver = new ChromeDriver(options);
		// 最大化浏览器窗口否则会造成找不到下一页按钮的bug
		driver.manage().window().maximize();
		// 打开链接
		driver.get(datum.url());
		// 创建一个随机数对象
		Random random = new Random();
		Thread.sleep(5000);

		try {
			// 获取下一页按钮
			WebElement nextElement = driver.findElement(By.className("next"));
			while (nextElement != null) {
				// 获取到我们需要的数据集合
				List<WebElement> Elements = driver.findElements(By.className("dataItem"));
				// for (Iterator<WebElement> iterator = Elements.iterator();
				// iterator.hasNext();) {
				// WebElement webElement = (WebElement) iterator.next();
				// String text = webElement.getText();
				// System.out.println(text);
				// }
				// 随机等待
				Thread.sleep(5000 - random.nextInt(2000));
				// 模拟浏览器点击下一页操作
				nextElement.click();
				// 随机等待
				Thread.sleep(5000 - random.nextInt(1000));
				// 重新获取刷新页面后的下一页按钮
				while (true) {
					try {
						nextElement = driver.findElement(By.className("next"));
						System.out.println("找到下一页");
						break;
					} catch (Exception e) {
						System.out.println("未找到下一页");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// 关闭浏览器
		driver.quit();
	}

	public static void main(String[] args) throws Exception {
		WenShuSpider wss = new WenShuSpider();
		// 创建一个基于伯克利DB的DBManager
		DBManager manager = new BerkeleyDBManager("crawl");
		// 创建一个Crawler需要有DBManager和Executor
		Crawler crawler = new Crawler(manager, wss);
		crawler.addSeed(
				"http://wenshu.court.gov.cn/List/List?sorttype=1&conditions=searchWord+1+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6");
		crawler.start(1);
	}

}
