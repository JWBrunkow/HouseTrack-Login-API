//package spring.login.api.service;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public MyUserDetailsService(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("user".equals(username)) {
//            return User.builder()
//                    .username("deez")
//                    .password(passwordEncoder.encode("nutz"))
//                    .roles("USER")
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }
//}
