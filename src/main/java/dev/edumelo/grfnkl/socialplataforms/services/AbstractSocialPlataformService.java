package dev.edumelo.grfnkl.socialplataforms.services;

import javax.annotation.PreDestroy;

import org.openqa.selenium.WebDriver;
import org.wildfly.common.Assert;

import dev.edumelo.grfnkl.socialplataformsusers.SocialPlataformUser;

public abstract class  AbstractSocialPlataformService <T extends SocialPlataformUser> implements SocialPlataformService<T> {
	
	protected abstract WebDriver getWebDriver();
	protected abstract AbstractSocialPlataformConfiguration getConfiguration();

	@PreDestroy
	public void preDestoy() {
		WebDriver webDriver = getWebDriver();
		
		Assert.assertNotNull(webDriver);
		webDriver.quit();
	}

}
