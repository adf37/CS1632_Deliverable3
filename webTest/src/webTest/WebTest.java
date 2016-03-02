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
		driver.get("http://www.reddit.com");
	}
	
//-------------------------------------------------------------------------------------------------------
//User Story: 
//As a redditor, I would like to login to my account, So that I can check and customize my profile page
	
	//Scenario 1: Given a correct username and a correct password,
	//			  When I try to access my profile page and select the comments section,
	//			  Then I should see the post with title "My University made a funny."			

	@Test
	public void checkUserComments() throws InterruptedException {
		WebElement link = driver.findElement(By.linkText("Log in or sign up"));
		link.click();
		Thread.sleep(2500);
		WebElement user = driver.findElement(By.id("user_login"));
		user.sendKeys("adf37");
		WebElement password = driver.findElement(By.id("passwd_login"));
		password.sendKeys("password");
		WebElement login = driver.findElement(By.xpath("//button[contains(text(), 'log in')]"));
		login.click();
		Thread.sleep(2500);
		WebElement profile = driver.findElement(By.linkText("adf37"));
		profile.click();
		Thread.sleep(500);
		WebElement comments = driver.findElement(By.linkText("comments"));
		comments.click();
		Thread.sleep(500);
		WebElement myComment = driver.findElement(By.linkText("My University made a funny."));
		System.out.println(myComment.getText());
		assertEquals("My University made a funny.", myComment.getText());
		//Thread.sleep(5000);
		driver.quit();
	}
	
	//Scenario 2: Given a correct username and a correct password,
		//			  When I try to access my profile page and select the upvoted section,
		//			  Then I should see the post with title "I made a Victorian map of Mars!."
	@Test
	public void checkUpVotes() throws InterruptedException{
		WebElement link = driver.findElement(By.linkText("Log in or sign up"));
		link.click();
		Thread.sleep(2500);
		WebElement user = driver.findElement(By.id("user_login"));
		user.sendKeys("adf37");
		WebElement password = driver.findElement(By.id("passwd_login"));
		password.sendKeys("password");
		WebElement login = driver.findElement(By.xpath("//button[contains(text(), 'log in')]"));
		login.click();
		Thread.sleep(2500);
		WebElement profile = driver.findElement(By.linkText("adf37"));
		profile.click();
		Thread.sleep(500);
		WebElement upVoteTab = driver.findElement(By.linkText("upvoted"));
		upVoteTab.click();
		Thread.sleep(500);
		WebElement myVote = driver.findElement(By.linkText("I made a Victorian map of Mars!"));
		System.out.println(myVote.getText());
		assertEquals("I made a Victorian map of Mars!", myVote.getText());
		driver.quit();
	}
	
	//Scenario 3: Given a correct username and a correct password,
		//			  When I try to access my profile page and select the downvoted section,
		//			  Then I should see the post with title "Since Fallout 4 doesn't have weapon condition, I decided to try
		//			  and animate what it might look like."
		@Test
	public void checkDownVotes() throws InterruptedException{
		WebElement link = driver.findElement(By.linkText("Log in or sign up"));
		link.click();
		Thread.sleep(2500);
		WebElement user = driver.findElement(By.id("user_login"));
		user.sendKeys("adf37");
		WebElement password = driver.findElement(By.id("passwd_login"));
		password.sendKeys("password");
		WebElement login = driver.findElement(By.xpath("//button[contains(text(), 'log in')]"));
		login.click();
		Thread.sleep(2500);
		WebElement profile = driver.findElement(By.linkText("adf37"));
		profile.click();
		Thread.sleep(500);
		WebElement downVoteTab = driver.findElement(By.linkText("downvoted"));
		downVoteTab.click();
		Thread.sleep(500);
		WebElement myVote = driver.findElement(By.linkText("Since Fallout 4 doesn't have weapon condition, I decided to try and animate what it might look like."));
		System.out.println(myVote.getText());
		assertEquals("Since Fallout 4 doesn't have weapon condition, I decided to try and animate what it might look like.", myVote.getText());
		driver.quit();
	}
	
	//Scenario 4: Given a correct username and a correct password,
		//			  When I try to edit my subscriptions and click the subscribe button next to /r/The_Donald: Donald J. Trump for President
		//			  Then the after reloading the page, the new subscription should appear in the my subreddits dropdown menu.
		//***this is assuming that the user has not subscribed to the subscription before this test***
	@Test
	public void subscribeToTrump() throws InterruptedException{
		WebElement link = driver.findElement(By.linkText("Log in or sign up"));
		link.click();
		Thread.sleep(1000);
		WebElement user = driver.findElement(By.id("user_login"));
		user.sendKeys("adf37");
		WebElement password = driver.findElement(By.id("passwd_login"));
		password.sendKeys("password");
		WebElement login = driver.findElement(By.xpath("//button[contains(text(), 'log in')]"));
		login.click();
		Thread.sleep(2500);
		WebElement mySubReddits = driver.findElement(By.xpath("//*[contains(@class, 'selected title')]"));
		mySubReddits.click();
		Thread.sleep(500);
		WebElement edit = mySubReddits.findElement(By.xpath("//a[text()='edit subscriptions']"));
		edit.click();
		Thread.sleep(2500);
		WebElement search = driver.findElement(By.className("sr-interest-bar"));
		WebElement box = search.findElement(By.className("query"));
		box.sendKeys("Donald Trump");
		Thread.sleep(1500);
		String oldWindow = driver.getWindowHandle();
		WebElement results = driver.findElement(By.className("results"));
		WebElement theDonald = results.findElement(By.linkText("/r/The_Donald"));
		theDonald.click();
		Thread.sleep(2000);
		int n = 0;
		for (String window : driver.getWindowHandles()){
			if (n == 0){ //first window is our previous window
				n++;
				continue;
			}
			driver.switchTo().window(window);
			Thread.sleep(500);
			WebElement subscribe = driver.findElement(By.linkText("subscribe"));
			subscribe.click();
			Thread.sleep(500);
			break;
		}
		driver.switchTo().window(oldWindow);
		
		driver.navigate().refresh(); //refresh the page to check subscriptions
		Thread.sleep(5000);
		WebElement subs = driver.findElement(By.xpath("//*[contains(@class, 'selected title')]"));
		subs.click();
		assertTrue(subs.findElement(By.xpath("//a[text()='The_Donald']")).isDisplayed());
		Thread.sleep(1000);
		driver.quit();
	}
}
