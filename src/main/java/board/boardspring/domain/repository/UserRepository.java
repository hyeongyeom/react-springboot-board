package board.boardspring.domain.repository;

import board.boardspring.domain.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
