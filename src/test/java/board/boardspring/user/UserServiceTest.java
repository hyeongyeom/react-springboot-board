package board.boardspring.user;

import board.boardspring.domain.entitiy.User;
import board.boardspring.domain.repository.UserRepository;
import board.boardspring.service.UserService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @DisplayName("mock 객체 생성 확인")
    @Test
    void mockNotNullTest(){
        assertThat(userService).isNotNull();
    }

    @DisplayName("게시판 등록")
    @Test
    void createUser() {
        User user=User.builder().email("wltnwkd244@naver.com").nickname("gyeom").password("abc123123").build();


        given(userRepository.save(any(User.class)))
                .willReturn(user);

        User savedUser=userService.createUser(user);

        assertThat(savedUser.getId()).isNotNull();
        verify(userRepository,times(1)).save((any(User.class)));

    }


}
