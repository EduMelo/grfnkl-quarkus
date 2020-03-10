package dev.edumelo.grfnkl.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;

class SocialPlataformParamConverter implements ParamConverter<SocialPlataformEnum> {

	@Override
	public SocialPlataformEnum fromString(String value) {
		return SocialPlataformEnum.valueOf(value.toUpperCase());
	}

	@Override
	public String toString(SocialPlataformEnum value) {
		return value.toString();
	}
	
}

@Provider
public class GrfnklParamConverter implements ParamConverterProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if(rawType == SocialPlataformEnum.class) {
			return (ParamConverter<T>) new SocialPlataformParamConverter();
		}
		return null;
	}

}
