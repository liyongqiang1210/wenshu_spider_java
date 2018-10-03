package com.spider.wenshu_spider;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		// 窗口最大化（这里必须将窗口最大化否则会造成selenium找到下一页按钮标签的错误）
		driver.manage().window().maximize();
		driver.get("http://wenshu.court.gov.cn/List/List?sorttype=1&conditions=searchWord+1+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6");
		// 创建一个随机数对象
		Random random = new Random();
		Thread.sleep(5000);
		// 获取下一页按钮
		WebElement nextElement = driver.findElement(By.className("next"));
		while (nextElement != null) {
			List<WebElement> Elements = driver.findElements(By.className("dataItem"));
			for (Iterator<WebElement> iterator = Elements.iterator(); iterator.hasNext();) {
				WebElement webElement = (WebElement) iterator.next();
				String text = webElement.getText();
				System.out.println(text);
			}
			// 随机等待10s以内
			Thread.sleep(5000-random.nextInt(2000));
			// 模拟浏览器点击下一页操作
			nextElement.click();
			// 随机等待
			Thread.sleep(5000-random.nextInt(1000));
			nextElement = driver.findElement(By.className("next"));
		}
	}
}
