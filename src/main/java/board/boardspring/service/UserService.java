package board.boardspring.service;

import board.boardspring.domain.entitiy.User;

import java.util.Optional;

public interface UserService {

    boolean createUser(User user);

    Optional<User> findUser(Long id);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
