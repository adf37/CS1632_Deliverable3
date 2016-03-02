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
	
//-------------------------------------------------------------------------------------------------------
//User Story: 
//As a redditor, I would like to login to my account, So that I can check and customize my profile page
	
	//Scenario 1: Check for comments that I have made
	//Will log in to account using account credentials and click the profile link highlighted
	//by my username. Then by clicking the comments tab I should be able to see all of the posts
	//that I have commented on.
	//Verify that this is true by going to the user's comments tab under their profile and check that a commented article title
	//is present, in this case "My University made a funny."
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
	
	//Scenario 2: Check for upvoted posts I have made
	//Will log in to account using account credentials and click the profile link highlighted by my username.
	//Then by clicking the upvoted tab I should be able to see all of the posts that I have upvoted.
	//Verify that this is true by going to the user's upvoted tab under their profile and check that an upvoted article title
	//is present, in this case "I made a Victorian map of Mars!."
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
		//Thread.sleep(5000);
		driver.quit();
	}
	
	//Scenario 3: Check for posts that I have downvoted
	//Will log in to account using account credentials and click the profile link highlighted by my username.
	//Then by clicking the downvoted tab I should be able to see all of the posts that I have downvoted.
	//Verify that this is true by going to the user's downvoted tab under their profile and check that an downvoted article title
	//is present, in this case "Since Fallout 4 doesn't have weapon condition, I decided to try and animate what it might look like."
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
		//Thread.sleep(5000);
		driver.quit();
	}
	
	
	
}
