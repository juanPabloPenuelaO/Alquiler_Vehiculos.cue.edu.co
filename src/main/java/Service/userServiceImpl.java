package Service;

import mapping.dtos.userDTO;
import mapping.mappers.UserMapper;
import model.user;
import repository.Repository;

import repository.userImpl.userRepositoryJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class userServiceImpl implements userService {

    private userRepositoryJDBC userRepository;

    public void UserServiceImpl(Connection connection) {
        this.userRepository = new userRepositoryJDBC(connection);
    }

    public userServiceImpl(Repository<user> userRepository) {
        this.userRepository = (userRepositoryJDBC) userRepository;
    }

    @Override
    public void addUser(userDTO userDTO) {
        userRepository.save(UserMapper.mapFromDTO(userDTO));
    }


    @Override
    public List<user> list() {
        return userRepository.list();
    }

    /*
    @Override
    public List<user> list() {
        try{
            return userRepository.list();
        } catch (SQLException throwables){
            throw new ServiceJDBCexception(throwables.getMessage(),
                    throwables.getCause());
        }
    }
*/
    @Override
    public userDTO byId(int id) {
        user user = userRepository.byId(id);
        return UserMapper.mapFrom(user user);
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
