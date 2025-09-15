package com.example.travel.security;

import com.example.travel.model.User;
import com.example.travel.model.enums.AuthProvider;
import com.example.travel.model.enums.Role;
import com.example.travel.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public OAuth2UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attrs = oAuth2User.getAttributes();

        String email = (String) attrs.get("email");
        String name = (String) attrs.getOrDefault("name", email);

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            // First-time login → create new user
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setRole(Role.USER);
            user.setProvider(AuthProvider.GOOGLE);
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            userRepository.save(user);
        } else {
            // Existing user → update name/provider if needed
            boolean updated = false;
            if (!name.equals(user.getName())) {
                user.setName(name);
                updated = true;
            }
            if (user.getProvider() != AuthProvider.GOOGLE) {
                user.setProvider(AuthProvider.GOOGLE);
                updated = true;
            }
            if (updated) {
                userRepository.save(user);
            }
        }

        // Build authorities
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

        return new DefaultOAuth2User(
                Collections.singleton(authority),
                attrs,
                "email"
        );
    }

}
