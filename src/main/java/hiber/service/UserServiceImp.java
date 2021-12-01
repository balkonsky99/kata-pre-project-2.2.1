package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public void addCar(String model, int series, User user) {
      userDao.addCar(model, series, user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car c where c.model = :model and c.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      Car car = query.getSingleResult();

      TypedQuery<User> query2=sessionFactory.getCurrentSession().createQuery("from User u where u.id = :id");
      query2.setParameter("id", car.getId());
      return query2.getSingleResult();
   }

}
