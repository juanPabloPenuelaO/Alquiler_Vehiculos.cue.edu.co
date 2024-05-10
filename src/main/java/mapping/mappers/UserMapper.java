package mapping.mappers;

import mapping.dtos.userDTO;
import model.user;

import java.util.List;

public class UserMapper {
    public static user mapFrom(userDTO userDTO) {
        return user.builder()
                .name(userDTO.name())
                .lastName(userDTO.lastName())
                .phoneNumber(userDTO.phoneNumber())
                .password(userDTO.password())
                .build();

    }

    public static userDTO mapFrom(user user) {
        return userDTO.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build();
    }
}
