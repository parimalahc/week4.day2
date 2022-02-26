package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement brandsLink = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brandsLink).perform();
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		if (driver.getTitle().contains("L'Oreal Paris")) {
			System.out.println("Page Title contains L'Oreal Paris");
		} else {
			System.out.println("Page Title does not contain L'Oreal Paris");
		}
		driver.findElement(By.xpath("//span[contains(text(),'Sort By')]")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		if (driver.findElement(By.xpath("//span[@class='filter-value']")).getText().equals("Shampoo")) {
			System.out.println("Filter is applied with Shampoo");
		} else {
			System.out.println("Filter is not applied with Shampoo");
		}
		driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		Select select = new Select(driver.findElement(By.xpath("//select[@title='SIZE']")));
		select.selectByVisibleText("175ml");
		System.out.println("Product MRP is: "
				+ driver.findElement(By.xpath("(//span[text()='MRP:']/following::span)[1]")).getText());
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		driver.findElement(By.xpath("//span[@class='cart-count']/parent::button")).click();
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='css-acpm4k']")));
		WebElement grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div/following::div"));		
		System.out.println("Grand total amount: "+grandTotal.getText());
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		if (driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText().equals(grandTotal)) {
			System.out.println("Grand total is same");
		} else {
			System.out.println("Grand total is not same");
		}

	}

}
