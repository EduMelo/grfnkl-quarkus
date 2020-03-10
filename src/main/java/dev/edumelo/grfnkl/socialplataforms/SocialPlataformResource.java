package dev.edumelo.grfnkl.socialplataforms;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

import dev.edumelo.grfnkl.socialplataformsusers.SocialPlataformUserResource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/social-plataforms")
public class SocialPlataformResource {
	
	@Context
	private ResourceContext resourceContext;

    @Path("/{plataformName}/users")
    public SocialPlataformUserResource getUser(@PathParam("plataformName") SocialPlataformEnum plataformName) {
    	log.trace("getUser. plataformName: " + plataformName);
    	return resourceContext.getResource(SocialPlataformUserResource.class);
    }
}