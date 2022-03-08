package emp.portal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
                            securedEnabled=true,
                            jsr250Enabled = true
)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger;



	@Autowired
    private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(Logger logger) {
        super();
        this.logger = logger;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
                
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http= http.exceptionHandling().authenticationEntryPoint((request, response,ex) -> {logger.error("Unauthorized Request-{}",ex.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());}).and();

        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/api/auth/signup","/api/auth/signin").permitAll().antMatchers("/emp/**").permitAll()
                      //antMatchers("/signin/").permitAll().antMatchers("/signup/").permitAll()
                //.antMatchers(HttpMethod.GET, "/api/**").permitAll()
                //.antMatchers("/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).httpBasic();
        http.cors();
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
    RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
    String hierarchy="ROLE_ADMIN>ROLE_Emp";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
}


@Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler expressionHandler=new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
}

 

}
