package emp.portal;

import java.util.*;

import emp.portal.exception.UnauthorizedExpection;
import emp.portal.repository.UserRepository;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UnauthorizedExpection {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UnauthorizedExpection();
        }
        return new org.springframework.security.core.userdetails.User(user.getId(),
                user.getPassword(), getAuthorities(user.getRoles()));
    }
@Transactional
private Collection<? extends GrantedAuthority> getUserAuthorities(String id) {
    User user=userRepository.findById(id).get();
    return getAuthorities(user.getRoles());

}
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }

   private List<String> getPermissions(Collection<Role> roles){
        List<String> permissions= new ArrayList<>();
        List<String> rolles=new ArrayList<>();
        Map<String,Permission> collection= new HashMap<>();
        List<Role> collection2=new ArrayList<>();
        for(Role role: roles){
            permissions.add(role.getName());
            collection.putAll(role.getPermissions());
            collection2.addAll(roles);
        }
        for (Map.Entry<String,Permission> item: collection.entrySet()){
            Assert.isInstanceOf(String.class,item.getKey(),"");
            permissions.add(item.getValue().getName());
        }
        for (Role item2: collection2){
            rolles.add(item2.getName());
        }

        return permissions;
    }
    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions){
        List<GrantedAuthority> authorities= new ArrayList<>();
        for (String permission:permissions){
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

}
