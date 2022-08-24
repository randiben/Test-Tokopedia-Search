package com.tokopedia.search;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SearchTokped {

	WebDriver driver = null;
	String baseUrl = "https://www.tokopedia.com/";
	
	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.navigate().to(this.baseUrl);
		driver.manage().window().maximize();
	}
	
	@Test (priority = 1)
	public void search () {
		driver.findElement(By.xpath("//input[@data-unify='Search']"))
		.sendKeys("beatles");
		driver.findElement(By.xpath("//button[@aria-label='Kirimkan']"))
		.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test (priority = 2)
	public void scrolling() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i <= 200; i++) {
			js.executeScript("window.scrollBy(0, " + i + ")");	
		}
		Thread.sleep(2000);
		
		List<WebElement> resultProduct = driver.findElements(By.xpath("//div[@data-testid='spnSRPProdPrice']"));			
		System.out.println("Jumlah produk yang ditampilkan = " + resultProduct.size());
	}
}
