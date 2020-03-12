package dev.edumelo.grfnkl.socialplataforms.services.twitter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.wildfly.common.Assert;

import dev.edumelo.grfnkl.socialplataformsusers.FollowerStatusEnum;
import dev.edumelo.grfnkl.socialplataformsusers.SocialPlataformUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class TwitterUser extends SocialPlataformUser {

	private enum PropertyType {HANDLE, NAME, FOLLOWER_STATUS}
	private String name;
	
	/**
	 * Tries to identify the property type by its value
	 * 
	 * @param value The value to be identified
	 * @return The type of the property
	 */
	private PropertyType identifyProperty(String value) {
		Assert.assertNotNull(value);
		if(value.startsWith("@")) {
			return PropertyType.HANDLE;
		} 
		
		FollowerStatusEnum followerStatus = FollowerStatusEnum.getFollowerStatusByTag(value);
		if(followerStatus != null) {
			return PropertyType.FOLLOWER_STATUS;
		}
		
		return PropertyType.NAME;
	}
	
	/**
	 * Set the property defined by propertyType of this instance with the value
	 * 
	 * @param propertyType The type of property
	 * @param value The value to be set
	 */
	private void setProperty(PropertyType propertyType, String value) {
		log.trace("setProperty. propertyType: " + propertyType + " value: " + value);
		
		switch (propertyType) {
		case HANDLE:
			setHandle(value);
			break;
		case NAME:
			setName(value);
			break;
		case FOLLOWER_STATUS:
			setFollowerStatus(FollowerStatusEnum.getFollowerStatusByTag(value));
			break;
		default:
			throw new IllegalArgumentException(String.format("Property type %s not expected", propertyType));
		}
	}

	/**
	 * Create TwitterUser from an Follower/Following anchor WebElement
	 * 
	 * @param followRelatedAnchor WebElement containing follow related user data
	 * @return a TwitterUser instance
	 */
	public static TwitterUser getTwitterUserFromUserDescriptor(WebElement followRelatedAnchor) {
		log.debug("getTwitterUserFromUserDescriptor. followRelatedAnchor: " + followRelatedAnchor);
		
		TwitterUser twitterUser = new TwitterUser();
		followRelatedAnchor.findElements(By.tagName("span")).stream()
			.map(e -> e.getText())
			.map(String::trim)
			.forEach(t -> twitterUser.setProperty(twitterUser.identifyProperty(t), t));
		
		return twitterUser;
	}

}
