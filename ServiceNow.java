package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev85882.service-now.com/navpage.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Mantra12$");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("incident" + Keys.ENTER);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[text()='New']")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		driver.findElement(By.linkText("survey user")).click();
		driver.switchTo().window(windows.get(0));
		driver.switchTo().frame("gsft_main");
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident number is: " + incidentNumber);
		driver.findElement(By.id("incident.short_description")).sendKeys("Hi I am a survey user");		
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		//driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//label[text()='Search']//following::input[1]"))
				.sendKeys(incidentNumber + Keys.ENTER);
		driver.findElement(By.linkText(incidentNumber)).click();
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\src\\main\\resources\\snaps\\img006.jpg");
		FileUtils.copyFile(src, dest);

	}

}
