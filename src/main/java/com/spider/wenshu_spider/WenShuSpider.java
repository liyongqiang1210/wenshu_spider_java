package com.spider.wenshu_spider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.utils.DateUtil;

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

	private static final Logger log = LoggerFactory.getLogger(WenShuSpider.class);

	public void execute(CrawlDatum datum, CrawlDatums next) {
		// 打开浏览器
		WebDriver driver = openDriver(datum);
		try {
			// 开始解析页面
			parsePage(driver);
		} catch (Exception e) {
			log.debug(e.getMessage());
		} finally {
			driver.quit();
		}
	}

	/**
	 * 打开浏览器
	 * 
	 * @param datum
	 * @return
	 */
	private WebDriver openDriver(CrawlDatum datum) {
		// 设置代理IP
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.managed_default_content_settings.images", 2);
		prefs.put("profile.managed_default_content_settings.javascript", 2);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("disable-infobars");
		// 设置代理ip
		String ip = "119.90.126.106:7777";
		options.addArguments("--proxy-server=http://" + ip);
		// 创建chrome浏览器驱动
		WebDriver driver = new ChromeDriver(options);
		// 最大化浏览器窗口否则会造成找不到下一页按钮的bug
		driver.manage().window().maximize();
		// 打开链接
		driver.get(datum.url());
		return driver;
	}

	/**
	 * 解析页面
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	private void parsePage(WebDriver driver) throws InterruptedException {
		// 创建一个随机数对象
		Random random = new Random();
		Thread.sleep(5000);

		// 获取下一页按钮
		WebElement nextElement = driver.findElement(By.className("next"));
		while (nextElement != null) {

			getCaseListPageData(driver);
			getCaseData(driver);

			Thread.sleep(5000 - random.nextInt(2000));
			// 点击下一页
			nextElement.click();
			Thread.sleep(5000 - random.nextInt(1000));

			// 重新获取刷新页面后的下一页按钮
			nextElement = driver.findElement(By.cssSelector("a.next"));
		}
	}

	/**
	 * 获取案例列表页面数据
	 * 
	 * @param driver
	 */
	private void getCaseListPageData(WebDriver driver) {
		// 获取案例的基本信息
		List<WebElement> Elements = driver.findElements(By.className("dataItem"));
		// 遍历我们需要的数据
		for (Iterator<WebElement> iterator = Elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String text = webElement.getText();
			log.debug(text);
		}
	}

	/**
	 * 获取案例的详细内容数据
	 * 
	 * @param driver
	 */
	private void getCaseData(WebDriver driver) {

		// 找到案例列表
		List<WebElement> caseDataList = driver
				.findElements(By.xpath("//div[@class='dataItem']/table/tbody/tr[1]/td/div/a"));

		// 遍历案例列表
		for (WebElement caseData : caseDataList) {

			caseData.click();// 点击案例链接

			// 获取初始页面句柄
			String handle = driver.getWindowHandle();
			// 获取所有页面的句柄，并循环判断不是初始的句柄
			for (String handles : driver.getWindowHandles()) {
				if (handles.equals(handle)) { // 如果是初始页面则进入下一个循环
					continue;
				}
				driver.switchTo().window(handles); // 切换到新的页面
			}
			// 获取我们要的内容
			WebElement caseDetailed = driver.findElement(By.className("div_doc_container"));
			System.out.println(caseDetailed.getText());
			// 获取所有页面的句柄，并循环判断不是初始页面的句柄
			for (String handles : driver.getWindowHandles()) {
				if (!handles.equals(handle)) { // 如果不是初始页面则关闭
					driver.close();
					driver.switchTo().window(handle);// 切换到初始页面
				}
			}

		}
	}

	/**
	 * 根据日期获取url列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getUrlList(String startDate, String endDate) {
		String url = "";
		List<String> urls = new ArrayList<String>(); // url集合
		List<String> days = DateUtil.getDays(startDate, endDate); // 日期集合
		for (String day : days) {
			url = "http://wenshu.court.gov.cn/list/list/?sorttype=1&number=7F3763NB"
					+ "&guid=f867a49e-3368-a21eab6b-07aa15204911"
					+ "&conditions=searchWord+1++%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6+%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6"
					+ "&conditions=searchWord++CPRQ++%E8%A3%81%E5%88%A4%E6%97%A5%E6%9C%9F:" + day + "%20TO%20" + day
					+ "";
			urls.add(url);
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		WenShuSpider wss = new WenShuSpider();
		// 创建一个基于伯克利DB的DBManager
		DBManager manager = new BerkeleyDBManager("crawl");
		// 创建一个Crawler需要有DBManager和Executor
		Crawler crawler = new Crawler(manager, wss);
		// 添加要爬取的url
		crawler.addSeed("https://www.csdn.net/");
		// 设置线程数
		crawler.setThreads(1);
		// 设置迭代次数
		crawler.start(1);
	}

}
