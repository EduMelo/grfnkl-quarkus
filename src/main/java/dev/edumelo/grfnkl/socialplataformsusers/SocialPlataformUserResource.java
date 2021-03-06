package dev.edumelo.grfnkl.socialplataformsusers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.eclipse.microprofile.faulttolerance.Retry;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Produces(MediaType.APPLICATION_JSON)
public class SocialPlataformUserResource {

	@Context
	private Request request;
	
	@Inject
	SocialPlataformUserService socialPlataformUserService;
	
	@GET
	@Path("/{userName}/following")
	@Retry(maxRetries = 3)
	public List<SocialPlataformUser> getFollowing(@PathParam("plataformName") SocialPlataformEnum plataformName, @PathParam("userName") String userName) {
		log.trace("getFollowers. serviceName: " + plataformName + " userName: " + userName);
		return socialPlataformUserService.getFollowing(plataformName, userName);
	}
	
}
