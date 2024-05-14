package Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapping.dtos.userDTO;
import mapping.mappers.UserMapper;
import model.user;
import repository.Repository;

import repository.userImpl.userRepositoryJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class userServiceImpl implements userService {

    @Inject
    private userRepositoryJDBC userRepository;

    @Override
    public void addUser(userDTO userDTO) {
        userRepository.save(UserMapper.mapFrom(userDTO));
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
        return UserMapper.mapFrom(user);
    }

    @Override
    public void save(userDTO user) {
        userRepository.save(UserMapper.mapFrom(user));
    }



@Override
    public void delete(int id) {

    userRepository.delete(id);
    }

}
