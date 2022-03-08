package emp.portal;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import emp.portal.payload.LoginDto;
import emp.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JacksonException;

import emp.portal.entity.AboutMe;
import emp.portal.entity.Employee;
import emp.portal.entity.SkillSet;
import emp.portal.repository.AboutMe_Repo;
import emp.portal.repository.Emp_Direct_Repo;
import emp.portal.repository.SkillSet_Repo;
import emp.portal.service.AboutMePojo;
import emp.portal.service.AboutMeService;
import emp.portal.service.AppConstants;
import emp.portal.service.Emp_Service;
import emp.portal.service.NotFoundException;
import emp.portal.service.SkillService;

//@RestController
//@RequestMapping("/emp")
//public class Emp_Controller {
//
//	
//	    @Autowired
//	    private Emp_Service service;
//	    
//	    @Autowired
//	    private Emp_Direct_Repo repository;
//
//	    
////	    @GetMapping
////	    public List<Employee> getAll(){
////	        return this.service.getAll();
////	    }
//	        
////	    @GetMapping("/name/{name}")
////	      public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String name)throws NotFoundException{
////	      List<Employee> response=service.findAllByName(name);
////          return ResponseEntity.ok(response);
////	    }
////	    
////	    @GetMapping("/id/{id}")
////	      public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("id") long id)throws NotFoundException{
////	 List<Employee> response=repository.findAllById(id);
////	    return ResponseEntity.ok(response);
////	    }
//
//	    
//	    @GetMapping
//	    //("/pagination/{offset}/{pageSize}")
//	    private List<Employee> getEmployeesWithPagination(
//	    		@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int offset,
//	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize) {
//	        Page<Employee> employeesWithPagination = service.findEmployeeWithPagination(offset, pageSize);
//	        List<Employee> emp=employeesWithPagination.getContent();
//	        return  emp;
//	    }
//	    @GetMapping("/name/{name}")
//	    //("/pagination/{offset}/{pageSize}")
//	    private ResponseEntity<List<Employee>> getNewEmployeesWithPagination(@PathVariable("name") String name,
//	    		@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int offset,
//	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize)throws NotFoundException
//	    {
//	        Page<Employee> employeesWithPagination = service.findNewEmployeeWithPagination(name,offset, pageSize);
//	        List<Employee> emp=employeesWithPagination.getContent();
//	        if(CollectionUtils.isEmpty(emp)){
//	            throw new NotFoundException("Name", "name", name);
//	 			}
//	        return ResponseEntity.ok(emp); 
//	    }
//	    @GetMapping("/id/{id}")
//	    //("/pagination/{offset}/{pageSize}")
//	    private ResponseEntity<List<Employee>> getIdEmployeesWithPagination(@PathVariable("id") int id,
//	    		@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int offset,
//	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize)throws NotFoundException
//	    {
//	        Page<Employee> employeesWithPagination = service.findIdEmployeeWithPagination(id,offset, pageSize);
//	        List<Employee> emp=employeesWithPagination.getContent();
//	        String idString=Integer.toString(id);
//	        if(CollectionUtils.isEmpty(emp)){
//	            throw new NotFoundException("Id", "emp_id",idString);
//	 			}
//	        return ResponseEntity.ok(emp);
//	    }
//	    
//	    @PostMapping("/add")
//	    public Employee addEmployee(@RequestBody Employee employee){
//	        return this.service.add(employee);
//	    }
//	    
//	    @DeleteMapping("/id/{id}")
//	    public  void delete(@PathVariable("id") long id){
//	        this.service.delete(id);
//	    }
//	    
//}


@CrossOrigin
@RestController
@RequestMapping("/emp")
public class Emp_Controller {

	private  LoginDto loginDto;
	    @Autowired
	    private Emp_Service service;
	    
	    @Autowired
	    private Emp_Direct_Repo repository;

		@Autowired
		private CustomUserDetailsService userDetailsService;


	@Autowired
	    private SkillService skillService;
	    
	    @Autowired
	    private SkillSet_Repo skillRepository;
	    
	    
	    @Autowired
	    private AboutMeService abtService;
	    
	    @Autowired
	    private AboutMe_Repo abtrepository;

		@Autowired
		private UserRepository userRepository;


	    
//	    @GetMapping
//	    public List<Employee> getAll(){
//	        return this.service.getAll();
//	    }
	        
//	    @GetMapping("/name/{name}")
//	      public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String name)throws NotFoundException{
//	      List<Employee> response=service.findAllByName(name);
//          return ResponseEntity.ok(response);
//	    }
//	    
//	    @GetMapping("/id/{id}")
//	      public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("id") long id)throws NotFoundException{
//	 List<Employee> response=repository.findAllById(id);
//	    return ResponseEntity.ok(response);
//	    }

	    
	    @GetMapping
		//	@PreAuthorize("hasAuthority('READ_Permission')")
	    //("/pagination/{offset}/{pageSize}")
	    private List<Employee> getEmployeesWithPagination(
	    		@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int offset,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize) {
	        Page<Employee> employeesWithPagination = service.findEmployeeWithPagination(offset, pageSize);
	        List<Employee> emp=employeesWithPagination.getContent();
	        return  emp;
	    }
	    @GetMapping("/name/{name}")
		//	@PreAuthorize("hasAuthority('READ_Permission')")
	    //("/pagination/{offset}/{pageSize}")
	    private ResponseEntity<List<Employee>> getNewEmployeesWithPagination(@PathVariable("name") String name,
	    		@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int offset,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize)throws NotFoundException
	    {
	        Page<Employee> employeesWithPagination = service.findNewEmployeeWithPagination(name,offset, pageSize);
	        List<Employee> emp=employeesWithPagination.getContent();
	        if(CollectionUtils.isEmpty(emp)){
	            throw new NotFoundException("Name", "name", name);
	 			}
	        return ResponseEntity.ok(emp); 
	    }
	    @GetMapping("/id/{id}")
		//	@PreAuthorize("hasAuthority('READ_Permission')")
	    //("/pagination/{offset}/{pageSize}")
	    private ResponseEntity<List<Employee>> getIdEmployeesWithPagination(@PathVariable("id") int id,
	    		@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int offset,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize)throws NotFoundException
	    {
	        Page<Employee> employeesWithPagination = service.findIdEmployeeWithPagination(id,offset, pageSize);
	        List<Employee> emp=employeesWithPagination.getContent();
	        String idString=Integer.toString(id);
	        if(CollectionUtils.isEmpty(emp)){
	            throw new NotFoundException("Id", "emp_id",idString);
	 			}
	        return ResponseEntity.ok(emp);
	    }
	    
	    /*-----------------EMP-DIR-HR---------------------------*/

	    @PostMapping("/add")
	//	@PreAuthorize("hasAuthority('WRITE_Permission_Dir')")
	    public Employee addEmployee(@RequestBody Employee employee){
	        return this.service.add(employee);
	    }
	    
	    @DeleteMapping("/id/{id}")
	//	@PreAuthorize("hasAuthority('DELETE_Permission_Dir')")
	    public  void delete(@PathVariable("id") long id){
	        this.service.delete(id);
	    }
	    
	    
	    /*----------------- SKILLS---------------------------*/
	    
//	    @GetMapping("/skill")
//	    public List<SkillSet> getAll(){
//	        return skillService.getAll();
//	    }
//	    @PostMapping("/skill/add")
//	    public SkillSet addSkill(@RequestBody SkillSet skillSet){
//	        return this.skillService.add(skillSet);
//	    }
//	    @DeleteMapping("/skill/{title}")
//	    public  void delete(@PathVariable("title") String title){
//	        this.skillService.delete(title);
//	    }
//	    @GetMapping("/skill/{title}")
//	    public SkillSet findByTitle(@PathVariable("title") String title)throws NotFoundException{
//	    	if(skillRepository.findById(title)==null)
//	    		throw new NotFoundException("title", "title",title);
//	        return skillService.findById(title);
//	    }
	    
	    /*----------------ABOUTME---------------------------*/
//	    @PostMapping("/aboutme")
//	    public AboutMe addAboutMe(@RequestBody AboutMe aboutMe) {
//	    	return this.abtService.add(aboutMe);
//	    }
	    
	    
	    @PostMapping("/aboutme")
	    public ResponseEntity<AboutMePojo> addAboutMe(@RequestBody AboutMe aboutMe)
	    throws JacksonException{
			AboutMe aboutMe1=new AboutMe();
			/* Validation for Name */
//			if(!(aboutMe.getName() == null)) {
//				if (!(aboutMe.getName().isEmpty())) {
//					if(!(aboutMe.getName().trim().isEmpty())) {
//
//						aboutMe1.setName(aboutMe.getName());
//					}
//					else {
//						return new ResponseEntity<>(new AboutMePojo(false,null,"Name Field is Required"),HttpStatus.BAD_REQUEST);
//					}
//				}
//				else {
//					return new ResponseEntity<>(new AboutMePojo(false,null,"Name Field is Required"),HttpStatus.BAD_REQUEST);
//				}
//			}
//			else {
//				return new ResponseEntity<>(new AboutMePojo(false,null,"Name Field is Required"),HttpStatus.BAD_REQUEST);
//			}

			/*------------------Employee Validation-------------------------------*/

			if(!(aboutMe.getEmpEmail() == null)) {
				if (!(aboutMe.getEmpEmail().isEmpty())) {
					if(!(aboutMe.getEmpEmail().trim().isEmpty())) {
						int size = aboutMe.getEmpEmail().length();
						char ch;
						int space = 0;
						for (int i = 0; i < size; i++) {
							ch = aboutMe.getEmpEmail().charAt(i);
							if (Character.isSpaceChar(ch))
								space++;

						}
						if (space != 0)
							return new ResponseEntity<>(new AboutMePojo(false,null,"No Space allowed"),HttpStatus.BAD_REQUEST);
						else
							aboutMe1.setEmpEmail(aboutMe.getEmpEmail());
					}
					else {
						return new ResponseEntity<>(new AboutMePojo(false,null,"Email field is required"),HttpStatus.BAD_REQUEST);
					}
				}
				else {
					return new ResponseEntity<>(new AboutMePojo(false,null,"Email field is required"),HttpStatus.BAD_REQUEST);
				}
			}
			else {
				return new ResponseEntity<>(new AboutMePojo(false,null,"Email field is required"),HttpStatus.BAD_REQUEST);
			}

			/*------------------------------Phone no Validation-------------------------------------------*/
			Long phone =aboutMe.getEmpPhoneNo();
			String no=String.valueOf(phone);
			if((no.matches("(^$|[0-9]{10})"))) {
				aboutMe1.setEmpPhoneNo(aboutMe.getEmpPhoneNo());
			}
			else {
				return new ResponseEntity<>(new AboutMePojo(false,null,"Phone no should be of 10 digits"),HttpStatus.BAD_REQUEST);
			}
			/**********************Nl-Experience Validation***************/
//			int exp=aboutMe.getEmpExp();
//			int nlexp=aboutMe.getEmpNLExp();
//			if(exp>=nlexp) {
//				aboutMe1.setEmpExp(aboutMe.getEmpExp());
//				aboutMe1.setEmpNLExp(aboutMe.getEmpNLExp());
//			}
//			else
//				return new ResponseEntity<>(new AboutMePojo(false,null,"Nl-Experience cannot be greater than Experience"),HttpStatus.BAD_REQUEST);

						AboutMePojo response;
						aboutMe1.setEmpExp((aboutMe.getEmpExp()));
						aboutMe1.setEmpNLExp(aboutMe.getEmpNLExp());
			aboutMe1.setEmpAboutMe((aboutMe.getEmpAboutMe()));
			aboutMe1.setSkills(aboutMe.getSkills());
			aboutMe1.setDateofjoining(aboutMe.getDateofjoining());
		//	User user=userRepository.findByEmail(aboutMe.getEmpEmail());
		//	aboutMe1.setId(user.getId());
			aboutMe1.setName(aboutMe.getName());
//aboutMe1.setId(abtrepository.findById(aboutMe.getEmpEmail()).get().getId());
			abtrepository.save(aboutMe1);
	    	try {
				return new ResponseEntity<>(new AboutMePojo(true,aboutMe1,"OK"),HttpStatus.OK);
			}catch(Error e) {
	    	return new ResponseEntity<>(new AboutMePojo(false,null,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }

		/********DELETE A SKILL BY EMPLOYEE***************/
	@DeleteMapping("/aboutme/{email}/skills/delete/{title}")
	public void deleteSkillByEmployee(@PathVariable("email") String email,@PathVariable("title") SkillSet title) {
		AboutMe aboutMe=abtrepository.findById(email).get();
		aboutMe.removeRoles(title);
	}
	/********ADD A SKILL BY EMPLOYEE***************/
	@PostMapping("/aboutme/{email}/skills/add/{title}")
	public void addskillByEmployee(@PathVariable("email") String email,@PathVariable("title") SkillSet title) {
		AboutMe aboutMe=abtrepository.findById(email).get();
		aboutMe.addSkills(title);
	}


	@PostMapping("/aboutme/skills")
	    public AboutMe addskillAboutMe(@RequestBody Set<SkillSet> skill) {
	    	AboutMe abt=new AboutMe();
	    	abt.setSkills(skill);
	    	//abtrepository.save(abt);
	    	return abt;
	    }
	    
	    @GetMapping("/skills/{title}")
	    public SkillSet searchSkill(@PathVariable String title) {
	    	return skillService.findById(title);
	    }
	    
	    /*------------------HR ADD SKILL---------------------*/
	    @PostMapping("/skills")
	    public List<SkillSet> postSkill(@RequestBody List<SkillSet> skill) {
	    	return this.skillService.add(skill);    	
	    }
	    
	    /*------------------HR DELETE SKILL---------------------*/
	    @DeleteMapping("/skills/{title}")
	    public void deleteSkill(@PathVariable("title") SkillSet title) {
//	    	AboutMe abt=new AboutMe();
//	    	abt.getSkills().remove(title);
////	    	for (SkillSet name :abt.getSkills()) {
////	    	    if(name.toString().equals(title))
////	    	    
////	    
	    	List<AboutMe> abt=abtrepository.findAll();
	    	for (AboutMe i :abt) {
	    		if(i.getSkills().contains(title))
	    			i.removeRoles(title);
	    	}
	    	this.skillService.delete(title);   
	    //	abtrepository.deleteByTitle(title);
	    	  	
	    }
	    
	    
	    /*----------------------GET PART.Empl--------------------*/
	    @GetMapping("/aboutme/email/{email}")
	    public AboutMe getAboutMe(@PathVariable("email") String email) {
	    	AboutMe abt=abtrepository.findById(email).get();
	    	return abt;
	    }


		/*-----------------AUTOFILL NAME AND EMAIL-------------------*/
	@GetMapping("/aboutme/{id}")
	public AboutMe getNameFromSignup(@PathVariable("id") String id) {
			User user = userRepository.getById(id);
			String name = user.getName();
			String email = user.getEmail();
			String userid=user.getId();
			AboutMe abt =new AboutMe();
			abt.setName(name);
			abt.setEmpEmail(email);
			abt.setId(userid);
			return abt;
		}

	    
	    @GetMapping("/aboutme/search/{title}")
	    public SkillSet getSkills(@PathVariable("title") String title) {
	    SkillSet skills=skillRepository.findByTitle(title);
		return skills;     
	    }
	    /*------------3rdMarch------------------*/
	    @GetMapping("aboutme/skills")
	    public List<SkillSet> getAllSkillsForEmployee(){
	    	List<SkillSet> skills=skillRepository.findAll();
	    	return skills;
	    }
	    
}





















