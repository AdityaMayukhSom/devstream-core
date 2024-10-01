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
import in.devstream.repository.RoleRepository;
import in.devstream.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRole(Role.ADMIN);
        Set<Role> roles = new HashSet<>();
        if (role != null) {
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

}
