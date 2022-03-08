package emp.portal;

import emp.portal.repository.PermissionRepository;
import emp.portal.repository.RoleRepository;
import emp.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository privilegeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User user = new User();
        Role role= new Role();
        Permission permission=new Permission();
        if (alreadySetup)
            return;
        Permission readPermission
                = createPrivilegeIfNotFound("READ_Permission");
        Permission writePrivilege
                = createPrivilegeIfNotFound("WRITE_Permission");
        Permission deletePermission
                = createPrivilegeIfNotFound("DELETE_Permission");
        Permission write_permission_skills
                = createPrivilegeIfNotFound("WRITE_Permission_Skills");
        Permission write_permission_dir
                = createPrivilegeIfNotFound("WRITE_Permission_Dir");
        Permission delete_permission_dir
                = createPrivilegeIfNotFound("DELETE_Permission_Dir");

        List<Permission> adminPrivileges = Arrays.asList(
                readPermission, writePrivilege);
        Map<String,Permission> hrPrivileges= new HashMap<>();
        hrPrivileges.put("p1",readPermission);
        hrPrivileges.put("p2",write_permission_dir);
        hrPrivileges.put("p3",writePrivilege);
        hrPrivileges.put("p4",deletePermission);
        hrPrivileges.put("p5",write_permission_skills);
        hrPrivileges.put("p6",delete_permission_dir);

        Map<String,Permission> adminPermission= new HashMap<>();
        adminPermission.put("p1",readPermission);
        adminPermission.put("p2",write_permission_dir);
        adminPermission.put("p3",writePrivilege);
        adminPermission.put("p4",deletePermission);
        adminPermission.put("p5",write_permission_skills);
        adminPermission.put("p6",delete_permission_dir);

        Map<String,Permission> managerPermission= new HashMap<>();
        managerPermission.put("p1",readPermission);
        managerPermission.put("p2",write_permission_dir);
        managerPermission.put("p3",writePrivilege);
        managerPermission.put("p4",deletePermission);
        managerPermission.put("p5",write_permission_skills);

        Map<String,Permission> empPermission= new HashMap<>();
        empPermission.put("p1",readPermission);
        empPermission.put("p3",writePrivilege);

        Map<String,Permission> accountPermission= new HashMap<>();
        accountPermission.put("p1",readPermission);
        accountPermission.put("p3",writePrivilege);

        Map<String,Permission> NL_ConnectPermission=new HashMap<>();
        NL_ConnectPermission.put("p1",readPermission);
        NL_ConnectPermission.put("p3",writePrivilege);
        NL_ConnectPermission.put("p4",deletePermission);
        NL_ConnectPermission.put("p5",write_permission_skills);

        Map<String,Permission> Emp_Permissions=new HashMap<>();
        Emp_Permissions.put("p1",readPermission);
        Emp_Permissions.put("p3",writePrivilege);
        Emp_Permissions.put("p4",deletePermission);
        Emp_Permissions.put("p2",write_permission_dir);
        Emp_Permissions.put("p6",delete_permission_dir);

        createRoleIfNotFound("ROLE_ADMIN",adminPermission);
        createRoleIfNotFound("ROLE_Emp",empPermission);
        createRoleIfNotFound("ROLE_HR",hrPrivileges);
        createRoleIfNotFound("ROLE_Manager",managerPermission);
        createRoleIfNotFound("ROLE_Account", accountPermission);
         alreadySetup = true;
    }
    @Transactional
    Permission createPrivilegeIfNotFound(String name) {
        Permission privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Permission(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
    @Transactional
    Role createRoleIfNotFound(
            String name, Map<String, Permission> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPermissions(privileges);
            roleRepository.save(role);
        }
        return role;
    }


}



