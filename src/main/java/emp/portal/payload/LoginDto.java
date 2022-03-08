package emp.portal.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDto implements Serializable {
	private String id;
	//private String email;
    private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*public String getEmail() {
	return email;
}
public void setEmail(String Email) {
	this.email = Email;
}*/
public String getPassword() {
	return password;
}
public void setPassword(String Password) {
	this.password = Password;
}


}
