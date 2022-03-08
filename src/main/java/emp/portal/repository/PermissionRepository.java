package emp.portal.repository;

import emp.portal.Permission;
import emp.portal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
    Permission findByName(String name);
}
