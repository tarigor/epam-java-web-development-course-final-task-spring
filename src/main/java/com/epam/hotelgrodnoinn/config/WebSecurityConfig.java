package com.epam.hotelgrodnoinn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Qualifier("userDetailsImpl")
//    @Autowired
//    private UserDetailsService userServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                разрешаем полный доступ для определенных страниц
                .antMatchers("/*", "/home", "/js/**", "/css/**"
//                        ,
//                        "/index", "/registration",
//                        "/downloads/**", "/static/**", "/shared/**", "/rest/**"
                ).permitAll()
                .anyRequest().authenticated()

//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin/*")
//                .hasRole("ADMIN")

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
        .and().csrf().disable();
//        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userServiceImpl).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

}
