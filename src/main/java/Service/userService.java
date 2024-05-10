package Service;

import mapping.dtos.userDTO;

import java.util.List;

import model.user;

public interface userService {
    void addUser(userDTO userDTO);

    List<user> list();

    userDTO byId(int id);

    void save(userDTO user);

    void delete(int id);

}
