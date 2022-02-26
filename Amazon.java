package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		String firstProductPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText().replaceAll("[^1-9]", "");
		System.out.println("Price of the first product: "+firstProductPrice);
		System.out.println("Number of customer ratings of the first product: "+driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText());
		Thread.sleep(3000);
        driver.findElement(By.xpath("(//i[@class='a-icon a-icon-popover'])[1]")).click();
		
		System.out.println("Percentage of ratings for 5 star: "+driver.findElement(By.xpath("(//a[contains(@title,'5 stars represent')])[3]")).getText());
		
		List<WebElement> mobileNames = driver.findElements(By.xpath("//span[contains(text(),'OnePlus 9 Pro')]"));
		mobileNames.get(0).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./src/main/resources/snaps/img009.jpg");
		FileUtils.copyFile(src, dest);
		
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);
		String cartSubTotal = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText().replaceAll("[^1-9]", "");
		System.out.println("Cart subtotal price: "+cartSubTotal.trim());
		if(cartSubTotal.equals(firstProductPrice)) {
			System.out.println("Price displayed is correct");
		}else {
			System.out.println("Price displayed is not correct");
		}
	

	}

}
