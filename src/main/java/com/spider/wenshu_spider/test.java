package com.spider.wenshu_spider;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test {

	public static void main(String[] args) throws InterruptedException, NoSuchFieldException, SecurityException {

		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.managed_default_content_settings.images", 2);
		prefs.put("profile.managed_default_content_settings.javascript", 2);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("disable-infobars");
		// 设置代理ip
		String ip = "119.90.126.106:7777";
		options.addArguments("--proxy-server=http://" + ip);
		WebDriver driver = new ChromeDriver(options);
//		DesiredCapabilities dc = new DesiredCapabilities();
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy(ip).setFtpProxy(ip).setSslProxy(ip);
//		dc.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
//		dc.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
//		System.setProperty("http.nonProxyHosts", "localhost");
//		dc.setCapability(CapabilityType.PROXY, true);
		driver.get("http://www.ip138.com/");
		Thread.sleep(50000);
		driver.quit();

	}
}
