package dev.edumelo.grfnkl.socialplataforms.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class SocialPlataformServiceFactory {

	@Inject
	@Any
	Instance<SocialPlataformService> socialPlataformService;
	
	public SocialPlataformService createSocialPlataformService(SocialPlataformEnum serviceName) {
		log.trace("createSocialPlataformService. serviceName: " + serviceName);
		
		Instance<SocialPlataformService> instance = this.socialPlataformService.select(new SocialPlataformServiceTypeNameLiteral(serviceName));
		
		if(!instance.isResolvable()) {
			throw new IllegalArgumentException(String.format("Service name %s not supported", serviceName));
		}
		
		return instance.get();
	}

}
