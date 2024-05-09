package Service;

import mapping.dtos.userDTO;
import mapping.mappers.UserMapper;
import model.user;
import repository.Repository;

import repository.userImpl.userRepositoryJDBC;

import java.util.List;

public class userServiceImpl implements userService {

    private Repository<user> userRepository;

    public void UserServiceImpl() {
        this.userRepository = new userRepositoryJDBC();
    }

    public userServiceImpl(Repository<user> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(userDTO userDTO) {
        userRepository.save(UserMapper.mapFromDTO(userDTO));
    }


    @Override
    public List<userDTO> list() {
        return userRepository.list().stream().map(UserMapper::mapFromModel).toList();
    }

    @Override
    public userDTO byId(int id) {
        user user = userRepository.byId(id);
        return UserMapper.mapFromModel(user);
    }

    @Override
    public void save(userDTO user) {
        userRepository.save(UserMapper.mapFromDTO(user));
    }



@Override
    public void delete(int id) {

    userRepository.delete(id);
    }

}
