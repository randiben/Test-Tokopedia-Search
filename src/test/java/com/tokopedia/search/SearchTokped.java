package com.tokopedia.search;


import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchTokped {

	WebDriver driver = null;
	String baseUrl = "https://www.tokopedia.com/";
	String expectedResult = null;
	
	@Given("User has accessed tokopedia site")
	public void user_has_accessed_tokopedia_site() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.navigate().to(this.baseUrl);
		driver.manage().window().maximize();
	}
	
	@When("User input product on search bar {string}")
	public void user_input_word_on_search_bar(String product) {
		driver.findElement(By.xpath("//input[@data-unify='Search']"))
		.sendKeys(product);
		driver.findElement(By.xpath("//button[@aria-label='Kirimkan']"))
		.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@When("Scroll down page until all product displayed")
	public void scroll_down_page_until_all_product_displayed() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i <= 200; i++) {js.executeScript("window.scrollBy(0, " + i + ")");
		}
		Thread.sleep(2000);
	}
	
	@Then("Validate total product displayed")
	public void validate_total_product_displayed() {
		//Get total actual product displayed 
		List<WebElement> resultProduct = driver.findElements(By.xpath("//div[@data-testid='spnSRPProdPrice']"));
		
		//Get total product displayed from search info
		String searchInfo = driver.findElement(By.className("css-8j9pkz")).getAttribute("innerHTML");
		String [] splitInfo = searchInfo.split(" ");
		expectedResult = splitInfo[3];
		
		assertEquals(resultProduct.size(), expectedResult);
	}
}
