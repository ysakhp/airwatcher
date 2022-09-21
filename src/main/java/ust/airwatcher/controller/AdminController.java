package ust.airwatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ust.airwatcher.service.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @PatchMapping("/activateuser/{username}/{active}")
    public ResponseEntity<String> userActivation(@PathVariable("username") String username, @PathVariable("active") Boolean isActive) {

        return ResponseEntity.ok(userService.activateUser(username, isActive));
    }


}
