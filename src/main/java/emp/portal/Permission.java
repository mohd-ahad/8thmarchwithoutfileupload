package emp.portal;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Permission implements GrantedAuthority {
    @Id
    private String name;
    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    private Collection<Role> roles;

    public Permission(String name) {
        this.name=name;
    }

    public Permission(String name, Collection<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    public Permission() {
    }

    @Override
    public String getAuthority() {
        return name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
