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

      for (int i = 0; i <= 4; i++) {
         String name = "name" + i;
         String lastName = "last_name" + i;
         String email = "email" + i;
         User user = new User(name, lastName, email);

         String model = "model" + i;
         String series = "series" + i;
         Car car = new Car(model, series);

         user.setCar(car);
         userService.add(user);
      }

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model= "+user.getCar().getModel());
         System.out.println("Car series= "+user.getCar().getSeries());
      }

      System.out.println(userService.getUsers("model1", "series1"));

      context.close();
   }
}
