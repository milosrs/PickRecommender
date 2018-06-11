package com.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.lol.security.RestAuthenticationEntryPoint;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lol.security.JWTAuthenticationTokenFilter;

@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private RestAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    		auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

   
    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JWTAuthenticationTokenFilter();
    }
   

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.formLogin().disable(); 
    	httpSecurity.csrf().disable();
        httpSecurity.exceptionHandling().authenticationEntryPoint(basicAuthenticationEntryPoint).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                ///.antMatchers(
	                    //    HttpMethod.GET,
	                     //   "/",
	                     ///   "/*.html",
	                     //   "/favicon.ico",
	                     //   "/**/*.html",
	                     //   "/**/*.css",
	                     ///   "/**/*.js"
	               // ).permitAll()
	                .antMatchers("*").permitAll()
	                .and()
	                .anonymous()
	                .and()
	                
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
                

        // Custom JWT based security filter
        
               

        // disable page caching
       /* httpSecurity
                .headers()
                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
                .cacheControl();*/
    }
   /*@Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter will ignore the below paths
        web.ignoring().antMatchers("/auth/**");
    }*/
}
