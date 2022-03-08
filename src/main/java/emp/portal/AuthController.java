package emp.portal;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import emp.portal.payload.LoginDto;
import emp.portal.payload.SignUpDto;
import emp.portal.repository.PermissionRepository;
import emp.portal.repository.RoleRepository;
import emp.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
	private JwtUtil jwtTokenUtil;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    private static final Gson gson =new Gson();

    @Autowired
    private PermissionModel Permission;

    public AuthController() {
    }


    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('READ_Permission')"+"|| hasAuthority('WRITE_Permission') ")
	public String firstPage() {
		return "Hello World";
	}

    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_Emp')")
    public String writePage() {
        return "Hello World";
    }


    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginDto loginDto) {
    	
        try{org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getId(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService
				.loadUserByUsername(loginDto.getId());
            User user = userRepository.getById(loginDto.getId());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
        List<String> roles=user.getRoles().stream().map(role ->role.getName()).sorted().collect(Collectors.toList());
        Map<String,Object> permissions=new HashMap<>();
        permissions.put("NlConnect",Permission.compareNLConnectPermission(roles));
        permissions.put("EmpDirectory",Permission.compareEmpDirPermission(roles));
		return ResponseEntity.ok(new AuthenticationResponse(jwt,permissions));
        }catch(Exception e){
            return ResponseHandler.generateresponse("ID or Password Incorrect",HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpDto signUpDto){

        if(userRepository.existsById(signUpDto.getId())){
            return ResponseHandler.generateresponse("Id is already Registered",HttpStatus.BAD_REQUEST);
        }
        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
        	return ResponseHandler.generateresponse("Email is already taken",HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();

        if(!(signUpDto.getName() == null)) {
            if (!(signUpDto.getName().isEmpty())) {
                if(!(signUpDto.getName().trim().isEmpty())) {

                    user.setName(signUpDto.getName());
                }
                else {
                    return ResponseHandler.validateresponse("Name Field is Required", "name", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseHandler.validateresponse("Name Field is Required", "name", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return ResponseHandler.validateresponse("Name Field is Required", "name", HttpStatus.BAD_REQUEST);
            }
        if(!(signUpDto.getId() == null)) {
            if (!(signUpDto.getId().isEmpty())) {
                if(!(signUpDto.getId().trim().isEmpty())) {
                    int size = signUpDto.getId().length();
                    char ch;
                    int space = 0;
                    for (int i = 0; i < size; i++) {
                        ch = signUpDto.getId().charAt(i);
                        if (Character.isSpaceChar(ch))
                            space++;

                    }
                    if (space != 0)
                        return ResponseHandler.validateresponse("No Space allowed", "id", HttpStatus.BAD_REQUEST);
                    else
                        user.setId(signUpDto.getId());

                }
                else {
                    return ResponseHandler.validateresponse("Id Field is Required", "id", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseHandler.validateresponse("Id Field is Required", "id", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return ResponseHandler.validateresponse("Id Field is Required", "id", HttpStatus.BAD_REQUEST);
        }
        if(!(signUpDto.getEmail() == null)) {
            if (!(signUpDto.getEmail().isEmpty())) {
                if(!(signUpDto.getEmail().trim().isEmpty())) {
                    int size = signUpDto.getEmail().length();
                    char ch;
                    int space = 0;
                    for (int i = 0; i < size; i++) {
                        ch = signUpDto.getEmail().charAt(i);
                        if (Character.isSpaceChar(ch))
                            space++;

                    }
                    if (space != 0)
                        return ResponseHandler.validateresponse("No Space allowed", "email", HttpStatus.BAD_REQUEST);
                    else
                    user.setEmail(signUpDto.getEmail());
                }
                else {
                    return ResponseHandler.validateresponse("Email Field is Required", "email", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return ResponseHandler.validateresponse("Email Field is Required", "email", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return ResponseHandler.validateresponse("Email Field is Required", "email", HttpStatus.BAD_REQUEST);
        }
        if(signUpDto.getPassword() == null) {
            return ResponseHandler.validateresponse("Password Field is Required", "password", HttpStatus.BAD_REQUEST);
        }
        if (signUpDto.getPassword().isEmpty()) {
            return ResponseHandler.validateresponse("Password Field is Required", "password", HttpStatus.BAD_REQUEST);
        }

        if(signUpDto.getPassword().trim().isEmpty()) {

            return ResponseHandler.validateresponse("Password Field is Required", "password", HttpStatus.BAD_REQUEST);
        }

        int maxlength=30,minlength=8,hasupper=0,haslower=0;
        int special=0,digits=0,space=0;
        char ch;

        int size=signUpDto.getPassword().length();

        if(size<minlength || size>maxlength)
        {
            return ResponseHandler.validateresponse("Password must have at least 1 Upper case,1 Lower case,1 Special character and 1 digit,Password Length 8 to 30 and No Space", "password", HttpStatus.BAD_REQUEST);
        }
        else
        {
            for (int i=0;i<size;i++)
            {
                ch=signUpDto.getPassword().charAt(i);
                if(Character.isUpperCase(ch))
                    hasupper++;
                else if (Character.isLowerCase(ch))
                    haslower++;
                else if(Character.isDigit(ch))
                    digits++;
                else if (Character.isSpaceChar(ch))
                    space++;
                else
                {
                    special++;
                }
            }

        }
        if(hasupper!=0 && haslower!=0 && digits!=0 && special!=0 && space==0)
        {
            if(signUpDto.getPassword().equals(signUpDto.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            }
            else
            {
                return ResponseHandler.generateresponse("Password and Confirm Password not same",HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            if (hasupper==0)
            return ResponseHandler.validateresponse("Password must have at least 1 Upper case,1 Lower case,1 Special character and 1 digit,Password Length 8 to 30 and No Space", "password", HttpStatus.BAD_REQUEST);
            if (haslower==0)
            return ResponseHandler.validateresponse("Password must have at least 1 Upper case,1 Lower case,1 Special character and 1 digit,Password Length 8 to 30 and No Space", "password", HttpStatus.BAD_REQUEST);
            if (digits==0)
            return ResponseHandler.validateresponse("Password must have at least 1 Upper case,1 Lower case,1 Special character and 1 digit,Password Length 8 to 30 and No Space", "password", HttpStatus.BAD_REQUEST);
            if (special==0)
            return ResponseHandler.validateresponse("Password must have at least 1 Upper case,1 Lower case,1 Special character and 1 digit,Password Length 8 to 30 and No Space", "password", HttpStatus.BAD_REQUEST);
            if (space!=0)
                return ResponseHandler.validateresponse("Password must have at least 1 Upper case,1 Lower case,1 Special character and 1 digit,Password Length 8 to 30 and No Space", "password", HttpStatus.BAD_REQUEST);
        }


        if((signUpDto.getConfirmPassword() == null))
        {
           return  ResponseHandler.validateresponse("Confirm Password Field is Required", "confirmPassword", HttpStatus.BAD_REQUEST);
        }

        if(signUpDto.getPassword().equals(signUpDto.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        }
        else
        {
            return ResponseHandler.generateresponse("Password and Confirm Password Are Not Same",HttpStatus.BAD_REQUEST);
        }

        Role roles = roleRepository.findByName("ROLE_HR");
       // Role roles2 =roleRepository.findByName("ROLE_Emp");
       // Role roles4 =roleRepository.findByName("ROLE_Manager");

       // Permission permission= permissionRepository.findByName("hasread");
        Set<Role> roles3 =new HashSet<>();
        roles3.add(roles);
        //roles3.add(roles2);
        //roles3.add(roles4);
        user.setRoles(roles3);

        userRepository.save(user);
        return ResponseHandler.generateresponse("User registered successfully",HttpStatus.OK);
    }
}
