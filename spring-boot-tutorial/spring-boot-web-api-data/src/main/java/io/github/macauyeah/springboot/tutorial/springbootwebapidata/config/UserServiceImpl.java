package io.github.macauyeah.springboot.tutorial.springbootwebapidata.config;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.macauyeah.springboot.tutorial.springbootwebapidata.entity.UserEntity;
import io.github.macauyeah.springboot.tutorial.springbootwebapidata.repo.UserRepo;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity defaultUser = new UserEntity();
        defaultUser.setUsername("admin");
        defaultUser.setPassword(passwordEncoder.encode("pass"));
        defaultUser.setRole("USER");
        defaultUser.setUuid(UUID.randomUUID().toString());
        userRepo.save(defaultUser);

        UserEntity user = userRepo.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        LOG.debug("got user uuid:{}, username:{}, role:{} from database", user.getUuid(), username, user.getRole());
        return new User(username, user.getPassword(), authorities);
    }
}