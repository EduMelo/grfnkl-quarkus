package dev.edumelo.grfnkl.socialplataformsusers;

import lombok.Data;

@Data
public class SocialPlataformUser {
	
	private String handle;
	private FollowerStatusEnum followerStatus = FollowerStatusEnum.NOT_FOLLOWER;
	
}
