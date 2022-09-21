package ust.airwatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ust.airwatcher.entity.User;
import ust.airwatcher.exception.UserNotFoundException;
import ust.airwatcher.repository.RoleRepo;
import ust.airwatcher.repository.UserRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepo;
    private Integer userId;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserName(username).orElseThrow(UserNotFoundException::new);

        this.userId = user.getId();
        return  new org.springframework.security.core.userdetails
                .User(user.getUserName(), user.getPassword(),getAuthority(user));    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    user.getRoles().stream().forEach(role ->
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
        );
        return authorities;
    }


    public Integer getLoggedInUserId() {
        return this.userId;

    }

}
