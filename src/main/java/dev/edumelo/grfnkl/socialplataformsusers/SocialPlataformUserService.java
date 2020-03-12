package dev.edumelo.grfnkl.socialplataformsusers;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<SocialPlataformUser> getFollowing(SocialPlataformEnum serviceName, String userName) {
		log.trace("getFollowers. serviceName: " + serviceName + " userName: " + userName);
		
		return socialPlataformServiceFactory
				.createSocialPlataformService(serviceName)
				.getFollowing(userName, "&lBwN#u0G6E^").stream()
				.map(f -> (SocialPlataformUser) f)
				.collect(Collectors.toList());
	}

}
