package dev.edumelo.grfnkl.socialplataforms.services;

import javax.enterprise.util.AnnotationLiteral;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SocialPlataformServiceTypeNameLiteral extends AnnotationLiteral<SocialPlataformServiceType> implements SocialPlataformServiceType {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2096978736850401576L;
	private final SocialPlataformEnum value;

	@Override
	public SocialPlataformEnum value() {
		return value;
	}

}
