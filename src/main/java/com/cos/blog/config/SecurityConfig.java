package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration // IoC
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;

	 // AuthenticationManager 빈 등록 - 이 빈을 등록함으로써 인증 처리를 진행할 수 있다.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	// BCryptPasswordEncoder 빈 등록 - 패스워드를 암호화
	@Bean // IoC가 돼용
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	/* 
	시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}  //뭔소릴까.......???????
	
	// SecurityFilterChain 빈 등록, HttpSecurity를 통한 URL 권한 설정
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() 											// csrf 토큰 비활성화(테스트시 걸어두는 게 좋음)
				.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll()   												//해당 경로에 대한 접근을 누구나 허용
				.anyRequest()												//설정한 경로 이외에 다른 모든 요청
				.authenticated()											//모든 요청에 대해 인증이 필요하다. 따라서 이 설정 이후의 모든 요청은 인증이 필요하게 된다.
			.and()
				.formLogin() 												// form 기반 로그인 활성화
				.loginPage("/auth/loginForm")					// 로그인 페이지 설정
				.loginProcessingUrl("/auth/loginProc")	// 시큐리티가 해당주소로 요청되는 로그인을 가로채서 대신 로그인해준다.
				.defaultSuccessUrl("/"); 								// 로그인 성공시 이동할 페이지

		return http.build();  //??
	}
}