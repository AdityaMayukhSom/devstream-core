package in.devstream.service;

import in.devstream.exception.UserNotFoundException;
import in.devstream.repository.UserRepository;
import in.devstream.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public boolean doesUserExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
