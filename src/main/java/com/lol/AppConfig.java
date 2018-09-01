package com.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lol.security.JWTAuthenticationTokenFilter;
import com.lol.security.RestAuthenticationEntryPoint;
import com.lol.security.SummonerAuthService;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	
	@Autowired
    private RestAuthenticationEntryPoint basicAuthenticationEntryPoint;
	
	@Autowired
	@Qualifier("summonerAuthService")
	private UserDetailsService summonerAuthService;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(summonerAuthService);
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(summonerAuthService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(summonerAuthService)
		.passwordEncoder(passwordEncoder());
		super.configure(auth);
	}
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(summonerAuthService);
	}
	

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JWTAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		JWTAuthenticationTokenFilter authenticationTokenFilter = new JWTAuthenticationTokenFilter();
		return authenticationTokenFilter;
	}

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception 
    {
    	httpSecurity
    		.csrf()
    		.disable()
    		.cors().and()
    		.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.anonymous().and()
    		.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
            .and()
    		.authorizeRequests().antMatchers("/auth/**").permitAll()
    		.anyRequest().authenticated();
    	
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Content-Type", "Date", "Total-Count", "Authorization")
                .maxAge(3600);
            }
        };
	}
}
