package webTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.Test;

public class WebTest {
	WebDriver driver = new FirefoxDriver();
	
	@Before
	public void getPage(){
		driver.get("http://reddit.com");
	}
	
	@Test
	public void test() {
		String title = driver.getTitle();
		assertTrue(title.contains("reddit"));
	}
	
	@After
	public void closePage(){
		driver.quit();
	}

}
