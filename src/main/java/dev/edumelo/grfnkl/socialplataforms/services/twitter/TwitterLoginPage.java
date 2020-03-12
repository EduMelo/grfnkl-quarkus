package dev.edumelo.grfnkl.socialplataforms.services.twitter;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wildfly.common.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwitterLoginPage {

	private static final String LOGIN_CAPTION_TEXT = "LOG IN TO TWITTER";

	private WebDriver webDriver;

	public TwitterLoginPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	public static String getTwitterLoginPage(String baseUrl) {
		Assert.assertNotNull(baseUrl);
		
		return String.format("%s/login", baseUrl);
	}
	
	@FindBy(css = "h1 > span")
	private WebElement loginCaption;
	
	@FindBy(css = "input[type='text']")
	private WebElement userNameInput;
	
	@FindBy(css = "input[type='password']")
	private WebElement passwordInput;
	
	@FindBy(css = "div[role='button']")
	private WebElement loginButton;
	
	private void insertUserName(String userName) {
		WebElement input = new WebDriverWait(webDriver, 50).until(ExpectedConditions.elementToBeClickable(userNameInput));
		input.sendKeys(userName);
	}
	
	private void insertPassword(String password) {
		WebElement input = new WebDriverWait(webDriver, 50).until(ExpectedConditions.elementToBeClickable(passwordInput));
		input.sendKeys(password);
	}
	
	private void clickLoginButton() {
		WebElement button = new WebDriverWait(webDriver, 50).until(ExpectedConditions.elementToBeClickable(loginButton));
		button.click();
	}
	
	public boolean isAlreadyLogged() {
		try {
			WebElement loginCaptionElement = new WebDriverWait(webDriver, 25).until(ExpectedConditions.elementToBeClickable(loginCaption));
			return !LOGIN_CAPTION_TEXT.equalsIgnoreCase(loginCaptionElement.getText().trim());			
		} catch (TimeoutException e) {
			return true;
		}
	}
	
	public void login(String userName, String password) {
		log.debug("login. userName: " + userName + " password: " + password);
		
		if(isAlreadyLogged()) {
			return;
		}
		insertUserName(userName);
		insertPassword(password);
		clickLoginButton();
	}
	
}
