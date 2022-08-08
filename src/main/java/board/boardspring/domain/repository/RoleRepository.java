package board.boardspring.domain.repository;

import board.boardspring.domain.entitiy.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
