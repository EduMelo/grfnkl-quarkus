package dev.edumelo.grfnkl.socialplataforms.services;

import java.util.List;

import dev.edumelo.grfnkl.socialplataformsusers.SocialPlataformUser;

public interface SocialPlataformService {
	public List<SocialPlataformUser> getFollowers(String userName);
}
