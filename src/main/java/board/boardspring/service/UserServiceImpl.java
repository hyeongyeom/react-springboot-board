package board.boardspring.service;

import board.boardspring.domain.entitiy.User;
import board.boardspring.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public boolean createUser(User user) throws DuplicateMemberException {
            log.info("Saving new user {} to the database",user.getEmail());
            Optional<User> chkUserEmail=userRepository.findByEmail(user.getEmail());
            if (chkUserEmail.isPresent()) {
                throw new DuplicateMemberException("User already exists");
            }
            userRepository.save(user);
            return true;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
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
