package dev.edumelo.grfnkl.webdriver;

import java.net.URL;
import java.util.Optional;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@ConfigProperties(prefix = "grfnkl.seleniumhub")
public class SeleniumHubConfig {
	
	enum EnvironmentType {LOCAL, REMOTE};
	enum PlataformType {LINUX, ANDROID};
	enum BrowserType {CHROME, FIREFOX, INTERNETEXPLORER}
	private String environment;
	private String browser;
	private String browserVersion = "";
	private String plataform;
	private Boolean windowMaximize;
	private Long implicitWait;
	private URL remoteDriveUrl;
	
	public EnvironmentType getEnvironment() {
		return Optional.ofNullable(environment)
				.map(String::toUpperCase)
				.map(s -> EnvironmentType.valueOf(s))
				.orElse(EnvironmentType.LOCAL);
	}
	
	public PlataformType getPlataform() {
		return Optional.ofNullable(plataform)
				.map(String::toUpperCase)
				.map(s -> PlataformType.valueOf(s))
				.orElse(PlataformType.LINUX);
	}
	
	public BrowserType getBrowser() {
		return Optional.ofNullable(browser)
				.map(String::toUpperCase)
				.map(s -> BrowserType.valueOf(s))
				.orElse(BrowserType.CHROME);
	}
	
}
