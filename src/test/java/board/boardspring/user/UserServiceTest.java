package board.boardspring.user;

import board.boardspring.domain.entitiy.User;
import board.boardspring.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        User user = new User(1L,"wltnwkd241@naver.com",123123,"choi" );
        userService.createUser(user);
        Optional<User> user1 = userService.findUser(1L);
        if(user1.isPresent()) {
            String email= user1.get().getEmail();
        assertThat(email).isEqualTo(user.getEmail());
        }
    }
}
