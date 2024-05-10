package repository;

import mapping.dtos.userDTO;
import model.reservations;

import java.util.List;
public interface Repository <T> {
    void addUser(userDTO userDTO);

    List<T> list();
    T byId(int id);
    void save(T t);
    void delete(int id);

    void update(reservations reservation);
}
