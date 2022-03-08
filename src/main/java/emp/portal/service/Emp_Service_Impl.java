package emp.portal.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import emp.portal.entity.Employee;
import emp.portal.repository.Emp_Direct_Repo;
import java.lang.*;

//@Service
//public class Emp_Service_Impl implements Emp_Service {
//	@Autowired
//	 private Emp_Direct_Repo repository;
//
//	@Autowired
//    private ModelMapper mapper;
//
//	
//	@Override
//	public List<Employee> getAll() {
//		return repository.findAll();
//	}
//
//	@Override
//	public EmployeeDTO findByName(String name) {
//		Employee emp=repository.findByName(name).orElseThrow(
//                () -> new NotFoundException("Name", "name", name));
//        return mapToDto(emp);
//		//int compareValue = repository.findByName(name).compareToIgnoreCase(name);
//	}
//	
//	@Override
//	public List<Employee> findAllByName(String name)throws NotFoundException {
//	List<Employee> emp=repository.findAll(name);
//			if(CollectionUtils.isEmpty(emp)){
//           throw new NotFoundException("Name", "name", name);
//			}
//	return emp;
//	
//	}
//	
//	
//	 private EmployeeDTO mapToDto(Employee employee) {
//		 EmployeeDTO postDto = mapper.map(employee, EmployeeDTO.class);
//	        return postDto;
//	    }
//
//	    // convert DTO to entity
//	    private Employee mapToEntity(EmployeeDTO employeeDTO) {
//	        Employee employee = mapper.map(employeeDTO, Employee.class);
//	        return employee;
//	    }
//		
////		boolean firstNameComp=name.equalsIgnoreCase
////		(repository.findByName(name).toString().substring(0,pos));
////		
////		int pos=0;
////		for(int i=0;i<name.length();i++) {
////			char ch=name.charAt(i);
////			if(ch== ' ' ) {
////				pos=i;
////				break;
////			}
////		}		 
////		
//
//	
//	
////	@Override
////	public Employee findById(int id, Pageable pageable) {
////		 Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
////	}
//	
//	 public Page<Employee> findEmployeeWithPagination(int offset,int pageSize){
//	        Page<Employee> employee = repository.findAll(PageRequest.of(offset, pageSize));
//	        return  employee;
//	    }
//
//	@Override
//	public Page<Employee> findNewEmployeeWithPagination(String name, int offset, int pageSize)throws NotFoundException {
//		Pageable pg=PageRequest.of(offset, pageSize);
//		Page<Employee> emp=repository.findAll(name,pg);
//		if(emp.getSize()<=0){
//	           throw new NotFoundException("Name", "name", name);
//				}
//		return emp;
//	}
//
//	@Override
//	public Page<Employee> findIdEmployeeWithPagination(long id, int offset, int pageSize)throws NotFoundException {
//		Pageable pg=PageRequest.of(offset, pageSize);
//		Page<Employee> emp=repository.findAllById(id,pg);
//		String name="id";
//		if(emp.getSize()<=0){
//	           throw new NotFoundException("Id", "emp_id", name);
//				}
//		return emp;
//	}
//
//	@Override
//	public Employee add(Employee employee) {
//		return repository.save(employee);
//	}
//
//	@Override
//	public void delete(long id) {
//		repository.deleteById(id);
//		
//	}
//	
//	
//
////	@Override
////	public List<Employee> findAll(String keyword) {
////		if(keyword!=null) {
////		return repository.findAll(keyword);
////	}
////		return repository.findAll();
////	}
//	
//	 
////	 public Page<Employee> findEmployeeWithPagination(String name,int offset,int pageSize){
////	        Page<Employee> employee = repository.findEmployeeByPagination(name,PageRequest.of(offset, pageSize));
////	        return  employee;
////	    }
//
//}


@Service
public class Emp_Service_Impl implements Emp_Service {
	@Autowired
	 private Emp_Direct_Repo repository;

	@Autowired
    private ModelMapper mapper;

	
	@Override
	public List<Employee> getAll() {
		return repository.findAll();
	}

	@Override
	public EmployeeDTO findByName(String name) {
		Employee emp=repository.findByName(name).orElseThrow(
                () -> new NotFoundException("Name", "name", name));
        return mapToDto(emp);
		//int compareValue = repository.findByName(name).compareToIgnoreCase(name);
	}
	
	@Override
	public List<Employee> findAllByName(String name)throws NotFoundException {
	List<Employee> emp=repository.findAll(name);
			if(CollectionUtils.isEmpty(emp)){
           throw new NotFoundException("Name", "name", name);
			}
	return emp;
	
	}
	
	
	 private EmployeeDTO mapToDto(Employee employee) {
		 EmployeeDTO postDto = mapper.map(employee, EmployeeDTO.class);
	        return postDto;
	    }

	    // convert DTO to entity
	    private Employee mapToEntity(EmployeeDTO employeeDTO) {
	        Employee employee = mapper.map(employeeDTO, Employee.class);
	        return employee;
	    }
		
//		boolean firstNameComp=name.equalsIgnoreCase
//		(repository.findByName(name).toString().substring(0,pos));
//		
//		int pos=0;
//		for(int i=0;i<name.length();i++) {
//			char ch=name.charAt(i);
//			if(ch== ' ' ) {
//				pos=i;
//				break;
//			}
//		}		 
//		

	
	
//	@Override
//	public Employee findById(int id, Pageable pageable) {
//		 Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//	}
	
	 public Page<Employee> findEmployeeWithPagination(int offset,int pageSize){
	        Page<Employee> employee = repository.findAll(PageRequest.of(offset, pageSize));
	        return  employee;
	    }

	@Override
	public Page<Employee> findNewEmployeeWithPagination(String name, int offset, int pageSize)throws NotFoundException {
		Pageable pg=PageRequest.of(offset, pageSize);
		Page<Employee> emp=repository.findAll(name,pg);
		if(emp.getSize()<=0){
	           throw new NotFoundException("Name", "name", name);
				}
		return emp;
	}

	@Override
	public Page<Employee> findIdEmployeeWithPagination(long id, int offset, int pageSize)throws NotFoundException {
		Pageable pg=PageRequest.of(offset, pageSize);
		Page<Employee> emp=repository.findAllById(id,pg);
		String name="id";
		if(emp.getSize()<=0){
	           throw new NotFoundException("Id", "emp_id", name);
				}
		return emp;
	}

	@Override
	public Employee add(Employee employee) {
		return repository.save(employee);
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
		
	}

//	@Override
//	public List<Employee> findAll(String keyword) {
//		if(keyword!=null) {
//		return repository.findAll(keyword);
//	}
//		return repository.findAll();
//	}
	
	 
//	 public Page<Employee> findEmployeeWithPagination(String name,int offset,int pageSize){
//	        Page<Employee> employee = repository.findEmployeeByPagination(name,PageRequest.of(offset, pageSize));
//	        return  employee;
//	    }

}














