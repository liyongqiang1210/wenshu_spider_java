package com.spider.wenshu_spider;

import java.util.Iterator;
import java.util.List;
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
		// Proxy proxy = new Proxy();
		// proxy.setHttpProxy("61.135.217.7:80222");
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
	public List<String> getUrlList(String startDate, String endDate) {
		String[] keyWordOne = { "合同", "利息", "利率", "合同约定", "民间借贷", "交通事故", "返还", "担保", "鉴定", "强制性规定", "借款合同", "人身损害赔偿",
				"误工费", "给付", "违约金", "贷款", "清偿", "驳回", "处分", "保证", "赔偿责任", "离婚", "违约责任", "交付", "交通事故损害赔偿", "夫妻关系", "传票",
				"买卖合同", "婚姻", "传唤", "民事责任", "债权", "债务人", "精神损害", "缺席判决", "残疾赔偿金", "债权人", "分公司", "保险合同", "追偿", "管辖",
				"第三人", "租赁", "承诺", "垫付", "票据", "变更", "股份有限公司", "抵押", "赔偿金", "连带责任", "劳动合同", "本案争议", "损害赔偿", "赔偿损失",
				"租金", "保证合同", "物业管理", "解除合同", "所有权", "建设工程", "劳动争议", "滞纳金", "保险人", "侵权行为", "共同债务", "程序合法", "投资", "财产保险",
				"保人", "出卖人", "抗辩权", "实际损失", "意思表示真实", "补充协议", "贷款人", "全面履行", "合同履行地", "代理", "被保险人", "赔偿数额", "主要责任",
				"不动产", "房屋买卖", "不履行", "保证期间", "标的物", "授权", "抚养费", "法定代表人", "反诉", "第三者责任险", "合同解除", "担保合同", "房屋租赁" };
		String[] keyWordTwo = { "非法占有", "减刑", "自首", "程序合法", "罚金", "减轻处罚", "从犯", "交通事故", "共同犯罪", "拘役", "故意犯", "管制",
				"附带民事诉讼", "交通肇事", "犯罪未遂", "没收", "鉴定", "主要责任", "偶犯", "财产权", "违法所得", "人身权利", "误工费", "人身损害赔偿", "立功", "自诉人",
				"财产刑", "扣押", "返还", "交付", "合法财产", "聚众", "伪造", "胁迫", "传唤", "寻衅滋事", "恶意透支", "共同故意", "所有权", "赔偿责任", "聚众斗殴",
				"过失犯", "过失", "驳回", "非法经营", "合同", "票据", "股份有限公司", "利用职务之便", "未成年人", "注册商标", "合伙", "假药", "强制措施", "残疾赔偿金",
				"交通事故损害赔偿", "冒用", "赔偿金", "专卖", "没收财产", "假冒", "代理", "精神损害", "着手", "利息", "传销", "分公司", "投资", "增值税", "挪用公款",
				"和解协议", "羁押", "剥夺政治权利", "合同诈骗", "赔偿协议", "非法所得", "行政拘留", "赔偿数额", "赔偿损失", "正当防卫", "财产保险", "违法行为", "贷款",
				"给付", "公共秩序", "和解", "追诉", "违背妇女意志", "变更", "单位犯罪", "婚姻", "聋哑人", "担保", "犯罪中止", "超载", "劳动报酬", "防卫过当", "租赁",
				"承诺", "处分" };
		String[] keyWordThree = { "程序合法", "驳回", "具体行政行为", "第三人", "不履行", "催告", "违法行为", "法定期限", "不予受理", "工伤", "利害关系",
				"管辖", "房屋征收", "拆迁", "所有权", "变更", "本案争议", "不动产", "合同", "房屋拆迁", "授权", "履行法定职责", "鉴定", "公共利益", "行政赔偿",
				"土地使用权", "建设用地", "交通事故", "房屋所有权", "建设工程", "处分", "撤回起诉", "行政拘留", "土地征收", "类似商品", "没收", "财产权", "宅基地",
				"给付", "国有土地使用权", "土地登记", "行政强制执行", "混淆", "变更登记", "动产", "近似商标", "驳回起诉", "利害关系人", "不作为", "投资", "确权",
				"法定代表人", "传唤", "拆迁安置", "交付", "土地承包经营权", "保险费", "诉讼标的", "房屋权属", "租赁", "强制性规定", "滞纳金", "公共秩序", "劳动合同",
				"意思表示真实", "公证", "房屋产权", "查封", "合法财产", "承包经营", "抵押", "共有", "注册商标", "伪造", "扣押", "保证", "代理人", "委托代理人",
				"赔偿损失", "返还", "书面形式", "强制措施", "超越职权", "股份", "自然资源", "违法所得", "承诺", "不可抗力", "分公司", "债权", "继承", "国家赔偿",
				"土地权属争议", "基金", "承包合同", "股权", "公积金", "恢复原状" };
		String[] keyWordFour = { "行政赔偿", "国家赔偿", "驳回", "赔偿义务", "具体行政行为", "财产权", "赔偿金", "程序合法", "强制措施", "违法行为", "直接损失",
				"精神损害", "不予受理", "赔偿数额", "扣押", "赔偿责任", "赔礼道歉", "法定期限", "恢复名誉", "拆迁", "利息", "侵权行为", "管辖", "冻结", "返还",
				"本案争议", "羁押", "房屋拆迁", "误工费", "查封", "恢复原状", "变更", "给付", "第三人", "所有权", "鉴定", "房屋征收", "不履行", "消除影响", "不动产",
				"合同", "损害赔偿", "合法财产", "利害关系", "宅基地", "刑事拘留", "行政拘留", "土地使用权", "返还财产", "撤回起诉", "拍卖", "溯及力", "驳回起诉",
				"土地征收", "赔偿损失", "授权", "房屋所有权", "变卖", "罚金", "建设用地", "债权", "建设工程", "处分", "取保候审", "实际损失", "投资", "租赁", "公证",
				"租金", "不可抗力", "精神损害赔偿", "公共利益", "拆迁安置", "履行法定职责", "保管", "没收", "精神损失费", "法定赔偿", "交付", "不作为", "交通事故",
				"抵押", "理赔", "补救措施", "先予执行", "担保", "请求权", "伪造", "工伤", "财产保全", "批准逮捕", "监视居住", "国有土地使用权", "没收财产", "法定代表人",
				"保证", "传唤", "房屋产权" };
		String[] keyWordFive = { "债权", "查封", "冻结", "扣押", "拍卖", "变卖", "合同", "不动产", "变更", "继续履行", "驳回", "处分", "合同约定",
				"所有权", "交付", "第三人", "不履行", "清偿", "抵押", "程序合法", "担保", "土地使用权", "投资", "利息", "和解协议", "债权人", "法定期限", "给付",
				"动产", "强制措施", "租赁", "债务人", "执行和解", "财产保全", "利害关系人", "股权", "财产刑", "房屋买卖", "抵押权", "本案争议", "罚金", "管辖",
				"房地产开发企业", "建设工程", "债权转让", "贷款", "买卖合同", "分公司", "民间借贷", "风险责任", "标的物", "迟延履行", "法定代表人", "共有", "租金",
				"股份有限公司", "公证", "房屋所有权", "优先受偿权", "财产权", "保证", "婚姻", "返还", "保证金", "承诺", "个人财产", "夫妻关系", "担保物权", "借款合同",
				"强制性规定", "离婚", "夫妻共同财产", "建设用地", "不完全履行", "意思表示真实", "房屋租赁", "抵偿", "共同债务", "企业法人", "不予受理", "国有土地使用权",
				"和解", "破产清算", "拆迁", "催告", "劳动争议", "案件受理费", "继承人", "质押", "承租人", "房产证", "授权", "有价证券", "共同财产", "实际履行",
				"变更登记" };
		List<String> days = DateUtil.getDays(startDate, endDate);
		return null;
	}

	public static void main(String[] args) throws Exception {
		List<String> days = DateUtil.getDays("2015-01-01", "2015-01-05");
		for (String day : days) {
			WenShuSpider wss = new WenShuSpider();
			// 创建一个基于伯克利DB的DBManager
			DBManager manager = new BerkeleyDBManager("crawl");
			// 创建一个Crawler需要有DBManager和Executor
			Crawler crawler = new Crawler(manager, wss);
			// 添加要爬取的url
			crawler.addSeed("http://wenshu.court.gov.cn/list/list/?sorttype=1&number=7F3763NB"
					+ "&guid=f867a49e-3368-a21eab6b-07aa15204911"
					+ "&conditions=searchWord+1++%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6+%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6"
					+ "&conditions=searchWord++CPRQ++%E8%A3%81%E5%88%A4%E6%97%A5%E6%9C%9F:" + day + "%20TO%20" + day
					+ "");
			// 设置线程数
			crawler.setThreads(1);
			// 设置迭代次数
			crawler.start(1);
		}
	}

}
