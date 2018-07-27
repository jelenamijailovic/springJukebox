package com.telnet.jukebox.spring.auth0;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;

@EnableWebSecurity
@Configuration
public class Auth0 extends WebSecurityConfigurerAdapter {

    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;
    @Value(value = "${auth0.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forHS256(apiAudience, issuer, secret.getBytes())
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/artists/top5artists").permitAll()
                .antMatchers(HttpMethod.GET, "/artists").permitAll()
                .antMatchers(HttpMethod.GET, "/artists/{artistId}/songs").permitAll()
                .antMatchers(HttpMethod.GET, "/songs/pagination/{page}").permitAll()
                .antMatchers(HttpMethod.GET, "/songs/top5songs").permitAll()
                .antMatchers(HttpMethod.GET, "/genres/{genreId}/songs").permitAll()
                .antMatchers(HttpMethod.GET, "/genres").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/traffic").authenticated()
                .antMatchers(HttpMethod.POST, "/traffic/recomended").authenticated();
            //  .antMatchers(HttpMethod.GET, "/api/private-scoped").hasAuthority("read:messages");
    }
}
