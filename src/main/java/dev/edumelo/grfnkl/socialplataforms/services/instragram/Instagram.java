package dev.edumelo.grfnkl.socialplataforms.services.instragram;
import java.util.Arrays;
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
@SocialPlataformServiceType(SocialPlataformEnum.INSTAGRAM)
@RequestScoped
public class Instagram extends AbstractSocialPlataformService<InstagramUser> {
	
	@Inject
	WebDriver webDriver;

	@Override
	protected WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	protected AbstractSocialPlataformConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstagramUser> getFollowing(String userName, String password) {
		log.trace("getFollowers. userName: " + userName);
		
		//TODO implementar método real
		return Arrays.asList(new InstagramUser("BudaJr"), new InstagramUser("VaiRenato"));
	}


}
