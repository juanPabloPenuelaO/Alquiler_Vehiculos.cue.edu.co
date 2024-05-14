package mapping.mappers;

import mapping.dtos.userDTO;
import mapping.dtos.vehiclesDTO;
import model.user;
import model.vehicles;
public class VehicleMapper {
    public static vehicles mapFrom(vehiclesDTO vehiclesDTO) {
        return vehicles.builder()
                .type(vehiclesDTO.type())
                .model(vehiclesDTO.model())
                .modelYear(vehiclesDTO.modelYear())
                .brand(vehiclesDTO.brand())
                .priceDay(vehiclesDTO.priceDay())
                .plate(vehiclesDTO.plate())
                .availability(vehiclesDTO.availability())
                .build();

    }

    public static vehiclesDTO mapFrom(vehicles vehicles) {
        return vehiclesDTO.builder()
                .type(vehicles.getType())
                .model(vehicles.getModel())
                .modelYear(vehicles.getModelYear())
                .brand(vehicles.getBrand())
                .priceDay(vehicles.getPriceDay())
                .plate(vehicles.getPlate())
                .availability(vehicles.getAvailability())
                .build();
    }
}