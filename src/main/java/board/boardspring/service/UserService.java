package board.boardspring.service;

import board.boardspring.domain.entitiy.User;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {

    boolean createUser(User user) throws Exception;

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
