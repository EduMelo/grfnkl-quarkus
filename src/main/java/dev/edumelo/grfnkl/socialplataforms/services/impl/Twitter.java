package dev.edumelo.grfnkl.socialplataforms.services.impl;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import dev.edumelo.grfnkl.socialplataforms.services.SocialPlataformService;
import dev.edumelo.grfnkl.socialplataforms.services.SocialPlataformServiceType;
import dev.edumelo.grfnkl.socialplataformsusers.SocialPlataformUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SocialPlataformServiceType(SocialPlataformEnum.TWITTER)
@RequestScoped
public class Twitter implements SocialPlataformService {

	@Override
	public List<SocialPlataformUser> getFollowers(String userName) {
		log.trace("getFollowers. userName: " + userName);
		
		//TODO implementar método real
		return Arrays.asList(new SocialPlataformUser("EduMelo"), new SocialPlataformUser("RenatoBrito"));
	}

}
