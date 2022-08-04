package board.boardspring.controller;


import board.boardspring.domain.entitiy.User;
import board.boardspring.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserRestController {

    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
        }catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(HttpStatus.CREATED);

    }
//    @PostMapping("/user/login")
//    public ResponseEntity<Void> login(@RequestBody User user) {
//
//    }
//
    @GetMapping("/user/{id}")
    public ResponseEntity<User> userList(@PathVariable Long id) {
        Optional<User> user=userService.findUserById(id);
        if(user.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
}
