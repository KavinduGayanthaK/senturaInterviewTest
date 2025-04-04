package lk.ijse.senturaInterviewTest.senturaInterviewTest.controller;

import jakarta.validation.Valid;
import lk.ijse.senturaInterviewTest.senturaInterviewTest.dto.UserDTO;
import lk.ijse.senturaInterviewTest.senturaInterviewTest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }



}
