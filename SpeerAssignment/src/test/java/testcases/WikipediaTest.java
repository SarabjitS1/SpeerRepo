package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WikipediaTest {
	
	WebDriver driver=null;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//resources//chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	
	@Test
	public void verifTest() throws InterruptedException {
		driver.get("https://en.wikibooks.org/wiki/Special:Search?search=science+book&go=Go&ns0=1&ns4=1&ns102=1&ns110=1&ns112=1");
		
		Assert.assertTrue(driver.getCurrentUrl().contains("wikibooks.org"));
		
		Random rn=new Random();
		int n=rn.nextInt(20-1)+1;
		
		for(int i=1;i<=n;i++) {
		
		List<WebElement> allLinksElement=driver.findElements(By.tagName("a"));
		//get all links in a list
		List<String> allLinks=new ArrayList<String>();
		for(WebElement ele:allLinksElement) {
		allLinks.add(ele.getAttribute("href"));	
		}
		
		driver.navigate().refresh();
		
		Thread.sleep(2000);
		List<WebElement> allNewLinksElement=driver.findElements(By.tagName("a"));
		
		List<String> allNewLinks=new ArrayList<String>();
		for(WebElement ele:allNewLinksElement) {
		allNewLinks.add(ele.getAttribute("href"));	
		}
		
		allNewLinks.removeAll(allLinks);
		//add new links to same old list
		allLinks.addAll(allNewLinks);
		
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}

}
