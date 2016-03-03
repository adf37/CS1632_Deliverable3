package webTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
		assertEquals("My University made a funny.", myComment.getText());
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
	
	//Scenario 5: Given that I am logged in with the correct username and password, when I click on the preferences tab, then 
	//I should be able to select/deselect from the list of preference options.
	//***This test is assuming that the checkbox has not already been check by the user***
	@Test
	public void adjustPreferences() throws InterruptedException{
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
		WebElement pref = driver.findElement(By.linkText("preferences"));
		pref.click();
		Thread.sleep(2500);
		driver.findElement(By.id("newwindow")).click();
		WebElement saveOptions = driver.findElement(By.xpath("//input[@class = 'btn']"));
		saveOptions.submit();
		Thread.sleep(2000);
		WebElement option = driver.findElement(By.id("newwindow"));
		assertTrue(option.isEnabled());
		driver.quit();
	}
	
	
//-----------------------------------------------------------------------------------------------
//User Story:
	//As a non-redditor
	//I would like to still navigate the site
	//So that I can read posts that I am interested in
	
	
	//Scenario 1: Given that I am not a redditor, when I try to select a subreddit from the top bar, then I should be navigated 
	//to that subreddit I selected
	@Test
	public void selectSubReddit() throws InterruptedException{
		WebElement link = driver.findElement(By.xpath("//*[contains(@class, 'sr-list')]"));
		WebElement list = link.findElement(By.id("sr-bar"));
		WebElement choice = list.findElement(By.linkText("FUNNY"));
		Thread.sleep(500);
		String c = choice.getText();
		choice.click();
		Thread.sleep(2500);
		assertEquals(driver.getTitle().toUpperCase(), c.toUpperCase());
		driver.quit();
	}
	
	//Scenario 2: Given that I am not a redditor, when I select the subreddit "funny", then I should be able to see the 
	//subreddit of the month.
	@Test
	public void selectPostSubReddit() throws InterruptedException{
		WebElement link = driver.findElement(By.xpath("//*[contains(@class, 'sr-list')]"));
		WebElement list = link.findElement(By.id("sr-bar"));
		WebElement choice = list.findElement(By.linkText("FUNNY"));
		Thread.sleep(500);
		choice.click();
		Thread.sleep(2500);
		List<WebElement> redditMonth = driver.findElements(By.xpath("//*[contains(@class, 'title may-blank')]"));
		WebElement reddit = null;
		for (WebElement e : redditMonth){
			if (e.getText().contains("Subreddit Of The Month")){
				reddit = e;
			}
		}
		assertNotNull(reddit); //check to see if the subreddit of the month is present
		Thread.sleep(1000);
		driver.quit();
	}
	
	//Scenario 3: Given that I am not a redditor, when I enter text in the search bar, then I should be shown filtered 
	//reddit posts based on my input
	@Test
	public void searchReddit() throws InterruptedException{
		WebElement form = driver.findElement(By.id("search"));
		WebElement searchBox = form.findElement(By.name("q"));
		Thread.sleep(2500);
		searchBox.sendKeys("USA");
		searchBox.submit();
		Thread.sleep(1000);
		assertEquals("reddit.com: search results", driver.getTitle());
		driver.quit();
	}
	
	//Scenario 4: Given that I am not a redditor, when I switch the language of the page, then the page should be updated to the 
	//language that I selected.
	@Test
	public void changeLanguage() throws InterruptedException{
		WebElement link = driver.findElement(By.linkText("English"));
		link.click();
		Thread.sleep(2500);
		WebElement dropDown = driver.findElement(By.id("lang"));
		Select s = new Select(dropDown);
		s.selectByValue("de");
		Thread.sleep(2500);
		WebElement saveOptions = driver.findElement(By.xpath("//input[contains(@class, 'btn')]"));
		saveOptions.submit();
		Thread.sleep(1000);
		WebElement newLang = driver.findElement(By.linkText("Deutsch"));
		assertEquals("Deutsch", newLang.getText());
		Thread.sleep(1000);
		driver.quit();
	}
	
	//Scenario 5: Given that I am not a redditor, when I change the time filter under the controversial category of the main page,
	//then the page should be updated to the new time frame that was selected.
	@Test
	public void changeTimeFrame() throws InterruptedException{
		WebElement category = driver.findElement(By.className("tabmenu"));
		WebElement controversial = category.findElement(By.xpath("//*[text() = 'controversial']"));
		controversial.click();
		Thread.sleep(2000);
		WebElement timer = driver.findElement(By.xpath("//span[@class = 'selected']"));
		String originalTime = timer.getText();
		timer.click();
		Thread.sleep(2000);
		WebElement newTime = timer.findElement(By.xpath("//*[text() = 'past hour']"));
		newTime.click();
		Thread.sleep(2000);
		WebElement timeLabel = driver.findElement(By.xpath("//span[@class = 'selected']"));
		assertNotEquals(originalTime, timeLabel.getText());
		driver.quit();
	}
	
	//Just in case any windows are not closed, close them at the conclusion of the tests
	@After
	public void cleanUp(){
		driver.quit();
	}
}
