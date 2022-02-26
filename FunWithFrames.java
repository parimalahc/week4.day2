package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunWithFrames {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame(0);
		WebElement clickBtn = driver.findElement(By.id("Click"));
		File src = clickBtn.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\src\\main\\resources\\snaps\\img007.jpg");
		FileUtils.copyFile(src, dest);
		
		driver.switchTo().defaultContent();
		List<WebElement> noOfFrames = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of frames in the webpage: "+noOfFrames.size());

	}

}
