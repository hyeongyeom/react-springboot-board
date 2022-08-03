package board.boardspring.controller;


import board.boardspring.domain.entitiy.User;
import board.boardspring.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        boolean flag = userService.createUser(user);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> userList(@PathVariable Long id) {
        Optional<User> user=userService.findUser(id);
        if(user.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
}
