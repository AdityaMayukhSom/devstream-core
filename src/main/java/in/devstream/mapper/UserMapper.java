package in.devstream.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//import in.devstream.dto.UserDto;
//import in.devstream.model.User;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

//    public UserDto fromUser(User user);
//
//    default public User toUser(UserDto userDto) {
//        User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getFullname());
//        user.setEnabled(true);
//        user.setAccountLocked(false);
//        user.setAccountExpired(false);
//        user.setCredentialsExpired(false);
//        return user;
//    }
}
