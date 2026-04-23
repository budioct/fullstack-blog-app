package dev.budioct.blog.services;

import org.springframework.security.core.userdetails.UserDetails;

// 7. add authentication service
public interface AuthenticationService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails);

    // filter token establishing context user
    UserDetails validateToken(String token);
}
