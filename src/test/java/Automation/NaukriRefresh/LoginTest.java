package Automation.NaukriRefresh;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

	@Test
	public void testLogin() throws IOException {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/");
		driver.findElement(By.id("login_Layer")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[contains(@placeholder,'Enter your active Email ID')]")));
		
		Properties property = new Properties();
		FileReader reader = new FileReader("src/test/resources/testdata/credentials.properties");
			property.load(reader);
			
			String username = property.getProperty("username");
			String password = property.getProperty("password");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter your active Email ID')]")).sendKeys(username);
		driver.findElement(By.cssSelector("input[placeholder$='password']")).sendKeys(password);
		driver.findElement(By.cssSelector(".loginButton")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".view-profile-wrapper a")));//
		driver.findElement(By.cssSelector(".view-profile-wrapper a")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("em[class*='icon edit']")));

		driver.findElement(By.cssSelector("em[class*='icon edit']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("exp-months-droope")));
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("em[class*='icon edit']")));
		driver.findElement(By.id("exp-months-droope")).click();
		driver.findElement(By.cssSelector("a[data-id='exp-months-droope_1']")).click();
		
		driver.findElement(By.id("saveBasicDetailsBtn")).click();
	}
}
