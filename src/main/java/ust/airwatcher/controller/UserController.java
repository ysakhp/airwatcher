package ust.airwatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ust.airwatcher.dto.UserDto;
import ust.airwatcher.entity.User;
import ust.airwatcher.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {


        return ResponseEntity.ok(userService.registerUser(userDto));
    }


    @PostMapping("/favourite/{city}")
    public ResponseEntity<String> addFavourites(@PathVariable String city) {
        return ResponseEntity.ok(userService.addFavourite(city));
    }


    @PostMapping(value = "/uploadphoto/{username}")
    public ResponseEntity<Object> uploadPhoto(@PathVariable("username") String userName, @RequestParam MultipartFile file) {

        return ResponseEntity.ok(userService.uploadPhoto(userName, file));

    }

}
