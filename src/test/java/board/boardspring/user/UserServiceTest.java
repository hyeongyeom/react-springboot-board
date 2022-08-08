package board.boardspring.user;

import board.boardspring.domain.entitiy.User;
import board.boardspring.service.UserService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        User user = new User(1L,"wltnwkd241@naver.com",123123,"choi" );
        try {
            userService.createUser(user);
        } catch (Exception e) {
            fail();
        }
        User user1 = userService.findUserById(1L);
        if(user1.isPresent()) {
            String email= user1.get().getEmail();
        assertThat(email).isEqualTo(user.getEmail());
        }
    }


    @Test
    public void duplicatedUser() {
        User user1 = new User(1L,"wltnwkd242@naver.com",123123,"choi" );
        User user2 = new User(2L,"wltnwkd242@naver.com",1231234,"choi1" );

        assertThrows(DuplicateMemberException.class,() -> {
            userService.createUser(user1);
            userService.createUser(user2);
        });


    }
}
