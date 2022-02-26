package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement mensLink = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mensLink).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		List<WebElement> noOfSportShoes = driver.findElements(By.xpath("//p[@class='product-title']"));
		System.out.println("Sport shoes count: " + noOfSportShoes.size());
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		Thread.sleep(4000);
		List<WebElement> shoesPriceList = driver.findElements(By.xpath("//span[contains(@id,'display-price')]"));
		List<Integer> shoesList = new ArrayList<Integer>();
		for (WebElement shoePrice : shoesPriceList) {
			shoesList.add(Integer.parseInt(shoePrice.getText().replace("Rs. ", "").replace(",", "").trim()));
		}
		for (int i = 0; i < shoesList.size() - 1; i++) {
			if (shoesList.get(i).compareTo(shoesList.get(i + 1)) == 0) {
				continue;
			}
			if (shoesList.get(i).compareTo(shoesList.get(i + 1)) > 0) {
				System.out.println("List is not sorted");
			}
		}
		System.out.println("List is sorted");
		Thread.sleep(3000);
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[text()='Rs. 900 - Rs. 1200'])[1]")).isDisplayed();
		driver.findElement(By.xpath("(//a[text()='Navy'])[1]")).isDisplayed();
		builder.moveToElement(driver.findElement(By.xpath("//p[contains(text(),'Red Tape Walking')]"))).perform();
		driver.findElement(By.xpath("(//div[contains(text(),'Quick View')])[1]")).click();
		System.out.println("Price of shoe: "+driver.findElement(By.className("payBlkBig")).getText());
		System.out.println("Discount offered for shoe: "+driver.findElement(By.xpath("//span[@class='payBlkBig']/following-sibling::span")).getText());
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./src/main/resources/snaps/img008.jpg");
		FileUtils.copyFile(src, dest);
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
		driver.close();
	}
}
