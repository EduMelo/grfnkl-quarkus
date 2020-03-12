package dev.edumelo.grfnkl.socialplataforms.services;

import java.util.List;

import dev.edumelo.grfnkl.socialplataformsusers.SocialPlataformUser;

public interface SocialPlataformService <T extends SocialPlataformUser> {
	public List<T> getFollowing(String userName, String password);
}
