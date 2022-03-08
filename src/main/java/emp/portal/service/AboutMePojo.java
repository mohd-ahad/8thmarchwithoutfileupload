package emp.portal.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import emp.portal.entity.AboutMe;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AboutMePojo {
	@JsonProperty("success")
	private boolean success;
	@JsonProperty("info")
	AboutMe aboutMe;
	@JsonProperty("message")
	private String message;
	
	public AboutMePojo(){}

	
	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public AboutMe getAboutMe() {
		return aboutMe;
	}


	public void setAboutMe(AboutMe aboutMe) {
		this.aboutMe = aboutMe;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AboutMePojo(boolean success, AboutMe aboutMe, String message) {
		this.success = success;
		this.aboutMe = aboutMe;
		this.message = message;
	}
}
