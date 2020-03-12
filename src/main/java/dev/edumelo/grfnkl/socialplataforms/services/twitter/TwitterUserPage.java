package dev.edumelo.grfnkl.socialplataforms.services.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wildfly.common.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwitterUserPage {
	
	private WebDriver webDriver;
	private JavascriptExecutor jsExecutor;

	public TwitterUserPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		jsExecutor = (JavascriptExecutor) webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	@FindBy(css = "div[data-testid='primaryColumn'] a[href$='/followers']")
	private WebElement followersAnchor;
	
	@FindBy(css = "div[data-testid='primaryColumn'] a[href$='/following']")
	private WebElement followingsAnchor;
	
	@FindBy(css = "div[data-testid='primaryColumn'] a[href$='/signup']")
	private WebElement loginButton;
	
	private void showFollowingsPage() {
		WebElement anchor = new WebDriverWait(webDriver, 300).until(ExpectedConditions.elementToBeClickable(followingsAnchor));
		anchor.click();
	}
	
	public static String getTwitterUserPage(String baseUrl, String userName) {
		Assert.assertNotNull(baseUrl);
		Assert.assertNotNull(userName);
		
		return String.format("%s/%s", baseUrl, userName);
	}
	
	private Long getBodyScrollSize() {
		return (Long) jsExecutor.executeScript("return document.body.scrollHeight");
	}
	
	private void scrollBody() {
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	private int getFollowingCount() {
		WebElement anchor = new WebDriverWait(webDriver, 50).until(ExpectedConditions.visibilityOf(followingsAnchor));
		return anchor.findElements(By.cssSelector("span > span")).stream()
			.map(WebElement::getText)
			.filter(Objects::nonNull)
			.map(String::trim)
			.filter(t -> !"FOLLOWING".equalsIgnoreCase(t))
			.map(Integer::parseInt)
			.findFirst()
			.orElseThrow();
	}
	
	/**
	 * 1 - Extract following count
	 * 2 - Change to Following page
	 * 3 - Loop through infinite scroll until the end of the page while extracting following user data
	 * The loop is broke if the read tries exceeds 2 times the number of following count 
	 * 
	 * @return A list of following twitter users
	 */
	public List<TwitterUser> getFollowingUsers() {
		log.debug("getFollowers. ");
		
		int maxLoopCount = getFollowingCount()*2;
		int actualLoopCount = 0;
		
		showFollowingsPage();
		Map<String, TwitterUser> userMap = new HashMap<String, TwitterUser>();
		long lastHeight = getBodyScrollSize();
		while(true) {			
			actualLoopCount++;
			if(actualLoopCount > maxLoopCount) {
				break;
			}
			
			try {
				new WebDriverWait(webDriver, 50).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
						By.cssSelector("[aria-label^='Timeline'] [style^='flex-basis']+div [aria-haspopup=false]"))).stream()
				.map(u -> TwitterUser.getTwitterUserFromUserDescriptor(u))
				.filter(u -> u.getHandle() != null)
				.forEach(u -> userMap.put(u.getHandle(), u));								
			} catch (StaleElementReferenceException e) {
				log.error("Element not found in DOM. Continuing search");
				continue;
			}
			scrollBody();
			
			long newHeight = getBodyScrollSize();
			log.debug(String.format("Last height %d. New height %d", lastHeight, newHeight));
			if(newHeight == lastHeight) {
				break;
			}
			lastHeight = newHeight;
		}
		
		ArrayList<TwitterUser> result = new ArrayList<TwitterUser>();
		result.addAll(userMap.values());
		return result;
	}
	
}
