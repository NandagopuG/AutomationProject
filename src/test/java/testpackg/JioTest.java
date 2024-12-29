package testpackg;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import baseclasspackg.JioBase;
import pagepackg.JioPage;


public class JioTest extends JioBase {
	
	@Test
	public void PageSource()
	{
		String src=driver.getPageSource();
		if(src.contains("Wellness"))
		{
			System.out.println("Content present");
		}
		else
		{
			System.out.println("Content not present");
		}
	}
	
	@Test
	public void srchClick() throws InterruptedException, IOException 
	{
		JioPage ob=new JioPage(driver);
		ob.setValues("Samsung Mobile");
		ob.clickSrch();
		ob.addtoCart();
		ob.cartLogin();
		ob.checkBrokenLinks();
		
	}
	@Test
	public void titleVerification()
	{
		String actualTitle=driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle="JioMart";
		if(expectedTitle.equals(actualTitle))
		{
			System.out.println("Title are same");
		}
			else
		{
				System.out.println("Title are not same");
		}
	}
	
	@Test
	public void hoverOverTabs() throws IOException, InterruptedException
	{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	Actions act=new Actions(driver);
	WebElement electronicsTab=(driver.findElement(By.xpath("//*[@id=\"nav_link_4\"]")));
	act.moveToElement(electronicsTab).perform();
    WebElement computersAccessoriesTab=(driver.findElement(By.xpath("//*[@id=\"nav_link_31995\"]")));;
	act.moveToElement(computersAccessoriesTab).perform();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav_level3_32138\"]")));
	driver.findElement(By.xpath("//*[@id=\"nav_level3_32138\"]")).click();
	Thread.sleep(3000);
	
	File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot, new File("./screenshot/hoverOverTabs.png"));

	}
	
	@AfterTest
    public void tearDown() {
        driver.quit();
    }
	

}
