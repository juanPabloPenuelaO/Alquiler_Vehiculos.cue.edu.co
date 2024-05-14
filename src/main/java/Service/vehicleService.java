package Service;

import mapping.dtos.vehiclesDTO;

import java.util.List;

import model.vehicles;

public interface vehicleService {
    void addVehicle(vehiclesDTO vehicleDTO);

    List<vehicles> list();

    vehiclesDTO byId(int id);

    void save(vehiclesDTO vehicle);

    void delete(int id);

}