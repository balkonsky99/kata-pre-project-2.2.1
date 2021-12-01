package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void addCar(String model, int series, User user);
    User getUserByCar(String model, int series);
    List<User> listUsers();
}
