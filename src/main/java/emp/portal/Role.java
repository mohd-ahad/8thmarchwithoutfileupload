package emp.portal;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
	     @Id
	    @Column(length = 60,nullable = false,unique = false)
	    private String name;

	public Role(String name) {
		this.name=name;
	}

	public Role() {
	}

	public Role( String name, Map<String,Permission> permissions) {
		this.name = name;
		this.permissions = permissions;
	}



		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "role_permission",
		joinColumns = @JoinColumn(name = "role",referencedColumnName = "name"),
		inverseJoinColumns = @JoinColumn(name = "permission",referencedColumnName = "name"))
private Map<String,Permission> permissions;


	@Override
	public String getAuthority() {
		return name;
	}
}
