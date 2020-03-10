package dev.edumelo.grfnkl.socialplataformsusers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import dev.edumelo.grfnkl.socialplataforms.services.SocialPlataformServiceFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestScoped
public class SocialPlataformUserService {

	@Inject
	SocialPlataformServiceFactory socialPlataformServiceFactory;
	
	public List<SocialPlataformUser> getFollowers(SocialPlataformEnum serviceName, String userName) {
		log.trace("getFollowers. serviceName: " + serviceName + " userName: " + userName);
		
		return socialPlataformServiceFactory
				.createSocialPlataformService(serviceName)
				.getFollowers(userName);
	}

}
