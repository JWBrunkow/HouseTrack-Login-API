//package spring.login.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.builder()
//                .username("deez")
//                .password(passwordEncoder.encode("nutz"))
//                .roles("USER")
//                .build();
//
//        userDetailsService.createUser(user);
//
//        return userDetailsService;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().permitAll());
//    }
//}
