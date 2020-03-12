package dev.edumelo.grfnkl.socialplataforms.services.twitter;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.openqa.selenium.WebDriver;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import dev.edumelo.grfnkl.socialplataforms.services.AbstractSocialPlataformConfiguration;
import dev.edumelo.grfnkl.socialplataforms.services.AbstractSocialPlataformService;
import dev.edumelo.grfnkl.socialplataforms.services.SocialPlataformServiceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SocialPlataformServiceType(SocialPlataformEnum.TWITTER)
@RequestScoped
public class Twitter extends AbstractSocialPlataformService<TwitterUser> {
	
	@Inject
	WebDriver webDriver;
	
	@Inject
	TwitterConfiguration config;

	@Override
	protected WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	protected AbstractSocialPlataformConfiguration getConfiguration() {
		return config;
	}

	@Override
	public List<TwitterUser> getFollowing(String userName, String password) {
		log.debug("getFollowers. userName: " + userName);
		webDriver.get(TwitterLoginPage.getTwitterLoginPage(config.getBaseUrl()));
		TwitterLoginPage loginPage = new TwitterLoginPage(webDriver);
		loginPage.login(userName, password);	
		
		webDriver.get(TwitterUserPage.getTwitterUserPage(config.getBaseUrl(), userName));
		TwitterUserPage userPage = new TwitterUserPage(webDriver);

		return userPage.getFollowingUsers();
	}



}
