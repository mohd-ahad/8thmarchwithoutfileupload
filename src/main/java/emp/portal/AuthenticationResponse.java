package emp.portal;


import java.io.Serializable;
import java.util.Map;

public class AuthenticationResponse implements Serializable {
    private final String jwt;
    private final Map<String,Object> permission;

    public AuthenticationResponse(String jwt,Map<String, Object> permission) {
        this.jwt = jwt;
        this.permission = permission;

    }

    public String getJwt() {
        return jwt;
    }

    public Map<String, Object> getPermission() {
        return permission;
    }

}