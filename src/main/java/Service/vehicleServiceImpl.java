package Service;

import mapping.dtos.vehiclesDTO;
import mapping.mappers.VehicleMapper;
import model.vehicles;
import repository.Repository;

import repository.vehiclesImpl.vehicleRepositoryJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class vehicleServiceImpl implements vehicleService {

    private vehicleRepositoryJDBC vehicleRepository;

    public void vehicleServiceImpl(Connection connection) {
        this.vehicleRepository = new vehicleRepositoryJDBC(connection);
    }

    public vehicleServiceImpl(Repository<vehicles> vehicleRepository) {
        this.vehicleRepository = (vehicleRepositoryJDBC) vehicleRepository;
    }

    @Override
    public void addVehicle(vehiclesDTO vehiclesDTO) {
        vehicleRepository.save(VehicleMapper.mapFrom(vehiclesDTO));
    }


    @Override
    public List<vehicles> list() {
        return vehicleRepository.list();
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
    public vehiclesDTO byId(int id) {
        vehicles vehicles = vehicleRepository.byId(id);
        return VehicleMapper.mapFrom(vehicles);
    }

    @Override
    public void save(vehiclesDTO vehiclesDTO) {
        vehicleRepository.save(VehicleMapper.mapFrom(vehiclesDTO));
    }



    @Override
    public void delete(int id) {

        vehicleRepository.delete(id);
    }

}