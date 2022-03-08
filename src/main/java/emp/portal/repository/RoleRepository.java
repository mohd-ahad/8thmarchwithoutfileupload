package emp.portal.repository;



import emp.portal.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
	Role findByName(String name);
	//Role findByUserid(Collection<Role> roles);

}
