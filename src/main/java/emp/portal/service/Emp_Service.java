package emp.portal.service;

//	import java.util.List;
//	import org.springframework.data.domain.Page;
//	import org.springframework.stereotype.Service;
//
//import emp.portal.entity.Employee;
//@Service
//public interface Emp_Service {
//	 public List<Employee> getAll();
//	// public EmployeeResponse getAll();
//	 public EmployeeDTO findByName(String name);
//	 public Page<Employee> findEmployeeWithPagination(int offset,int pageSize);
//	 public List<Employee> findAllByName(String name) throws NotFoundException;
//	// public Employee findByAccountName(String accountName);
//	 public Page<Employee> findNewEmployeeWithPagination(String name,int offset, int pageSize)throws NotFoundException;;
//	 
//	 public Page<Employee> findIdEmployeeWithPagination(long id,int offset, int pageSize)throws NotFoundException;;
//	// public Page<Employee> findEmployeeWithPagination(String name,int offset,int pageSize);
//	//public List<Employee> findAll(String keyword);
//	// public Employee findById(int id , Pageable pageable);
//	 
//	 public Employee add(Employee employee);
//	 public void delete(long id);
//}


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import emp.portal.entity.Employee;

@Service
public interface Emp_Service {
	 public List<Employee> getAll();
	// public EmployeeResponse getAll();
	 public EmployeeDTO findByName(String name);
	 public Page<Employee> findEmployeeWithPagination(int offset,int pageSize);
	 public List<Employee> findAllByName(String name) throws NotFoundException;
	// public Employee findByAccountName(String accountName);
	 public Page<Employee> findNewEmployeeWithPagination(String name,int offset, int pageSize)throws NotFoundException;;
	 
	 public Page<Employee> findIdEmployeeWithPagination(long id,int offset, int pageSize)throws NotFoundException;;
	// public Page<Employee> findEmployeeWithPagination(String name,int offset,int pageSize);
	//public List<Employee> findAll(String keyword);
	// public Employee findById(int id , Pageable pageable);
	 
	 	public Employee add(Employee employee);
	    public void delete(long id);
	 
	 
}