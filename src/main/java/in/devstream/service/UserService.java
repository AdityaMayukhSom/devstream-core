package in.devstream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.devstream.dto.UserDto;
import in.devstream.exception.UserNotFoundException;
import in.devstream.mapper.UserMapper;
import in.devstream.model.User;
import in.devstream.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto findUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public boolean doesUserExistsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
