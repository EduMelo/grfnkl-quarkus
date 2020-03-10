package dev.edumelo.grfnkl.socialplataforms.services;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;

@Qualifier
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, PARAMETER })
public @interface SocialPlataformServiceType {

	SocialPlataformEnum value();
	
}
