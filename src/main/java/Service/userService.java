package Service;

import mapping.dtos.userDTO;

import java.util.List;

public interface userService {
    void addUser(userDTO userDTO);

    List<userDTO> list();

    userDTO byId(int id);

    void save(userDTO user);

    void delete(int id);
}
