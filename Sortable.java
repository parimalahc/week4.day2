package week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame(0);
		Actions builder = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//li[text()='Item 2']"));
		WebElement element2 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		builder.dragAndDropBy(element1, 50, 50).perform();
		builder.clickAndHold(element2).release(element1).perform();

	}

}
