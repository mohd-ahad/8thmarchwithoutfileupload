package emp.portal.repository;

import emp.portal.Role;
import emp.portal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	 User findByEmail(String email);
	   // Optional<User> findByUsernameOrEmail(String email);
	    Boolean existsByEmail(String email);
		@Query(value = "SELECT * FROM user where id=?1",nativeQuery = true)
		User findDetailsById(String id);
	//	Role findByrolesIn(Set<Role> roles);


		/*User findByID(String id);
		Boolean existsByID(String id);*/


}
