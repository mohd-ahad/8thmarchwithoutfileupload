package emp.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.portal.entity.NewApp;

@Repository
public interface NewAppRepository extends JpaRepository<NewApp, Long> {
}
