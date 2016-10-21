package print_bd;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import print_bd.entity.User;
import print_bd.entity.UserRole;
import print_bd.repository.UserRepository;
import print_bd.repository.UserRoleRepository;
import print_bd.service.UserService;

import java.util.Date;

/**
 * Created by sereo_000 on 23.09.2016.
 */

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class);
    }
    @Bean
    public CommandLineRunner loadData(UserRepository repository,
                                      UserRoleRepository roleRepository, UserService userService) {
        return (args) -> {
            //добавить немного  кастомеров
            UserRole role;
            UserRole role1;
            UserRole role2;
            UserRole role3;
            User vaulin;
            roleRepository.save(role3 = new UserRole(false, false, true, true,false,"Старший химик"));
            roleRepository.save(role = new UserRole(false, false, true, true,false,"Химик"));
            roleRepository.save(role1 = new UserRole(false, true, false, false,true,"Заведующий КАнЛ"));
            roleRepository.save(role2 = new UserRole(false, true, false, false,true,"Заместитель заведующего КАнЛ"));

            String pass = "1";
            pass = DigestUtils.md5Hex(pass);
            repository.save(vaulin = new User("","", "Ваулин","test1@mail.ru",pass,"",""));
            User ne4aeva;

            repository.save(ne4aeva = new User("","", "Нечаева","test2@mail.ru",pass,"",""));
            User serduk;
            User skok;
            repository.save(serduk = new User("","", "Сердюк","test3@mail.ru",pass,"",""));
            repository.save(skok = new User("","", "Скок","test4@mail.ru",pass,"",""));
            userService.changeUserRole(vaulin,role3);
            userService.changeUserRole(ne4aeva,role);
            userService.changeUserRole(serduk,role1);
            userService.changeUserRole(skok,role2);
            // выборка всех кастомеров
        };
    }
}
