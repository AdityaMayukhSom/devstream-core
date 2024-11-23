package in.devstream.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.devstream.dto.UserDto;
import in.devstream.mapper.UserMapper;
import in.devstream.model.Role;
import in.devstream.model.User;
import in.devstream.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto userDto) {
        User user = UserMapper.MAPPER.toUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        userRepository.save(user);
    }

}
