package dev.edumelo.grfnkl.webdriver;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@Dependent
public class BrowserControllerDriver {

	@Inject
	SeleniumHubConfig config;

	@Produces
	WebDriver createDriver() {
		switch (config.getEnvironment()) {
		case REMOTE:
			return createRemoteDriver();
		case LOCAL:
			return createLocalDriver();
		default:
			throw new IllegalArgumentException(
					String.format("Environment type %s not implemented", config.getEnvironment()));
		}		
	}
	
	private WebDriver createLocalDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	private WebDriver createRemoteDriver() {
		WebDriver driver = new RemoteWebDriver(config.getRemoteDriveUrl(), configOptions());
		if (Boolean.TRUE.equals(config.getWindowMaximize())) {
			driver.manage().window().maximize();
		}
		return driver;
	}

	private MutableCapabilities configOptions() {
		MutableCapabilities options;
		
		switch (config.getBrowser()) {
		case FIREFOX:
			options = new FirefoxOptions();
			break;
		case INTERNETEXPLORER:
			options = new InternetExplorerOptions();
			break;
		case CHROME:
		default:
			options = new ChromeOptions();
		}

		options.setCapability("plataform", config.getPlataform().name());
		options.setCapability("version", config.getBrowserVersion());
		
		return options;
	}

}
