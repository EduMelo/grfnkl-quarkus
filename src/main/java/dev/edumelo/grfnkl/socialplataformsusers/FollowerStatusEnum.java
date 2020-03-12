package dev.edumelo.grfnkl.socialplataformsusers;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public enum FollowerStatusEnum {
	FOLLOWER(new String[]{"Segue você", "Follows you"}), 
	NOT_FOLLOWER(new String[]{});

	@Getter
	private List<String> possibleIndentifiableTags;
	
	FollowerStatusEnum(String[] possibleIndentifiableTags) {
		this.possibleIndentifiableTags = Arrays.asList(possibleIndentifiableTags);
	}
	
	public static FollowerStatusEnum getFollowerStatusByTag(String tag) {
		if(tag.isBlank()) {
			return NOT_FOLLOWER;			
		} else if(FOLLOWER.getPossibleIndentifiableTags().contains(tag)) {
			return FOLLOWER;
		}
		return null;
	}
	
}
