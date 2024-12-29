package pagepackg;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class JioPage {
	WebDriver driver;
	@FindBy(xpath="//*[@id=\"autocomplete-0-input\"]")
	WebElement jioSearch;
	@FindBy(xpath="//*[@id=\"variant_607431532\"]/div[2]/div[2]/div/div[2]")
	WebElement mobileSearch;	
	@FindBy(xpath="/html/body/main/section/section[2]/div[1]/div[2]/div[1]/div/div[3]/div[1]/button")
	WebElement addCart;
	@FindBy(xpath="//*[@id=\"btn_minicart\"]/img")
	WebElement cartt;
		
	public JioPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void setValues(String search)
	{
		jioSearch.sendKeys(search,Keys.ENTER);
	}
	
	public void clickSrch()
	{
		mobileSearch.click();
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
	}
	public void addtoCart()
	{
		addCart.click();
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-3000)");	
	}
	
	public void cartLogin() throws InterruptedException {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click()", cartt);
	    Thread.sleep(8000);
	}
	
	public void checkBrokenLinks() throws IOException, InterruptedException
	{
		Thread.sleep(5000);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links)
		{
		String href = link.getAttribute("href");
		if (href != null && !href.isEmpty())
		{
		checkLink(href);
		} 
		}
		}
		private void checkLink(String urlString) throws IOException 
		{
		URL url;
		try
		{
		url = new URL(urlString);
		}
		catch(Exception e) 
		{
		System.out.println("Other Urls: "+ urlString);
		return;
		}
		if (!url.getProtocol().equals("https") && !url.getProtocol().equals("http")) 
		{
		System.out.println("Skipping non-http/https link: "+ urlString);
		return;
		}
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.connect();
		int responseCode = httpURLConnection.getResponseCode();
		if (responseCode >= 400) 
		{
		System.out.println("Broken link: " + urlString + " Response code: " + responseCode);
		}
		else
		{
		System.out.println("Valid link: " + urlString + " Response code: " + responseCode);
		}
		
		}
	
}
