package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("FROM Car");
      return query.getResultList();
   }

   public void getUserByModelAndSeries(Car car) {
      Query<User> query = sessionFactory.getCurrentSession()
              .createQuery("FROM User user LEFT OUTER JOIN FETCH user.car WHERE user.car.model =: model AND user.car.series =: series", User.class);
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      User user = query.uniqueResult();
      System.out.println(user);
   }

}
