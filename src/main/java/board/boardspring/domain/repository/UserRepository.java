package board.boardspring.domain.repository;

import board.boardspring.domain.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
