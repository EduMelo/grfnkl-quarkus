package dev.edumelo.grfnkl.socialplataforms.services.twitter;

import dev.edumelo.grfnkl.socialplataforms.services.AbstractSocialPlataformConfiguration;
import io.quarkus.arc.config.ConfigProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@ConfigProperties(prefix = "grfnkl.twitter")
public class TwitterConfiguration implements AbstractSocialPlataformConfiguration {

	private String baseUrl;
	
}
