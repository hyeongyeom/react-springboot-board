package board.boardspring.service;

import board.boardspring.domain.entitiy.Role;
import board.boardspring.domain.entitiy.User;
import board.boardspring.domain.repository.RoleRepository;
import board.boardspring.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(email);
        if(user==null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("user not found");
        } else {
            log.info("User found in the database: {}" , email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        log.info("Saving new user {} to the database",user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepository.save(role);

    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}",roleName,email);
        User user=userRepository.findByEmail(email);
        Role role=roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Fetching user {}",email);
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }
}
