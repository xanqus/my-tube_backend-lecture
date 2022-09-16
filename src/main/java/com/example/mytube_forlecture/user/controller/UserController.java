package com.example.mytube_forlecture.user.controller;

import com.example.mytube_forlecture.user.domain.User;
import com.example.mytube_forlecture.user.dto.UserDto;
import com.example.mytube_forlecture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        System.out.println("email: " + user.getEmail());

        userService.join(user);

        return "user";
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody User user) throws Exception {
        UserDto loginedUser = userService.login(user);
        return loginedUser;
    }
}
