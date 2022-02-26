package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//span[text()='From Contact']/following::td/a/img")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));		
		driver.findElement(By.xpath("(//div[contains(@class,'firstName')]//a)[2]")).click();
		driver.switchTo().window(windows.get(0));
		driver.findElement(By.xpath("//span[text()='To Contact']/following::td/a/img")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windows1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(windows1.get(1));		
		driver.findElement(By.xpath("(//div[contains(@class,'firstName')]//a)[3]")).click();
		driver.switchTo().window(windows1.get(0));
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		if(driver.getTitle().equals("View Contact | opentaps CRM")) {
			System.out.println("Page title is verified");
		}else {
			System.out.println("Page title is not verified");
		}
		
		

	}

}
