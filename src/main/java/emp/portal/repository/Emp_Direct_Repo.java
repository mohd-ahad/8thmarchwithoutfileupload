package emp.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import emp.portal.entity.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
//public interface Emp_Direct_Repo  extends JpaRepository<Employee, Long> {
//Optional<Employee> findByName(String name);
//@Query(value="SELECT * FROM employee e WHERE e.Emp_Name LIKE %?1%"+
//" OR e.Account_Mapped LIKE %?1%",
//nativeQuery=true)
//List<Employee> findAll(String name);
//List<Employee> findAllByNameContainingIgnoreCase(String name);
//
//List<Employee> findAllById(long id);
////@Query(value="SELECT * FROM employee e WHERE e.",
////nativeQuery=true)
//@Query(value="SELECT * FROM employee e WHERE e.Emp_Name LIKE %?1%"+
//" OR e.Account_Mapped LIKE %?1%",nativeQuery=true)
//
//Page<Employee> findAll(String name, Pageable pg);
//Page<Employee> findAllById(long id, Pageable pg);
//
//
//	//public List<Employee> findByAccount(String account);
////	@Query("from Employee as e where e.id=:id")
////	public Page<Employee> findEmployeeById(@Param("id")int id,Pageable pageable);
////	List<Employee> findAll(String accountName);
//	
//}

@Repository
public interface Emp_Direct_Repo  extends JpaRepository<Employee, Long> {
Optional<Employee> findByName(String name);
@Query(value="SELECT * FROM employee e WHERE e.Emp_Name LIKE %?1%"+
" OR e.Account_Mapped LIKE %?1%",
nativeQuery=true)
List<Employee> findAll(String name);
List<Employee> findAllByNameContainingIgnoreCase(String name);

List<Employee> findAllById(long id);
//@Query(value="SELECT * FROM employee e WHERE e.",
//nativeQuery=true)
@Query(value="SELECT * FROM employee e WHERE e.Emp_Name LIKE %?1%"+
" OR e.Account_Mapped LIKE %?1%",nativeQuery=true)

Page<Employee> findAll(String name, Pageable pg);
Page<Employee> findAllById(long id, Pageable pg);


	//public List<Employee> findByAccount(String account);
//	@Query("from Employee as e where e.id=:id")
//	public Page<Employee> findEmployeeById(@Param("id")int id,Pageable pageable);
//	List<Employee> findAll(String accountName);
	
}














