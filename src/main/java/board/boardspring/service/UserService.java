package board.boardspring.service;

import board.boardspring.domain.entitiy.Role;
import board.boardspring.domain.entitiy.User;


import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User createUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String email,String roleName);

    Optional<User> findUserById(Long id);

    User findUserByEmail(String email);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
