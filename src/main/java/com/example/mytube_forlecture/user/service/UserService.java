package com.example.mytube_forlecture.user.service;

import com.example.mytube_forlecture.user.dao.UserRepository;
import com.example.mytube_forlecture.user.domain.User;
import com.example.mytube_forlecture.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void join(User user) {
        userRepository.save(user);
    }

    public UserDto login(User user) throws Exception {
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());
        if(opUser.isPresent()) {
            User loginedUser = opUser.get();
            System.out.println("db user password: " + loginedUser.getPassword());
            System.out.println("전송받은 user password: " + user.getPassword());
            if(loginedUser.getPassword().equals(user.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setId(loginedUser.getId());
                userDto.setEmail(loginedUser.getEmail());
                userDto.setUsername(loginedUser.getUsername());

                return userDto;
            }
            throw new Exception();
        }
        throw new Exception();
    }
}
