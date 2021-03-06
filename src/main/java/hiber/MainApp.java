package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new Car("Peugeot", 308));
//      userService.add(new Car("BMW", 201));
//      userService.add(new Car("Mercedes-Benz", 500));
//      userService.add(new Car("Nissan", 450));

      userService.add(new User(new Car("Peugeot", 308), "User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(new Car("BMW", 201), "User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(new Car("Mercedes-Benz", 500), "User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User(new Car("Nissan", 450), "User4", "Lastname4", "user4@mail.ru"));



      List<User> users = userService.listUsers();
      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
         System.out.println(user);

      }

      List<Car> cars = userService.listCars();
      for (Car car : cars) {
//         System.out.println("Id = " + car.getId());
//         System.out.println("Model = " + car.getModel());
//         System.out.println("Series = " + car.getSeries());
//         System.out.println();
         System.out.println();
         userService.getUserByModelAndSeries(car);
         System.out.println();
      }



      context.close();
   }
}
