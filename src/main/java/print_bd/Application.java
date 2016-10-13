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
                                      UserRoleRepository roleRepository) {
        return (args) -> {
            //добавить немного  кастомеров
            UserRole role;
            UserRole role1;
            UserRole role2;
            UserRole role3;
            User ivan;
            roleRepository.save(new UserRole(false, false, true, false,"role1"));
            roleRepository.save(role = new UserRole(true, false, true, false,"role2"));
            roleRepository.save(role1 = new UserRole(true, true, true, true,"role3"));
            roleRepository.save(role2 = new UserRole(true, true, true, false,"role4"));
            roleRepository.save(role3 = new UserRole(false, true, false, false,"role5"));

            String pass="1";
            pass= DigestUtils.md5Hex(pass);
            repository.save(ivan=new User("88005553535",role,"Иван", "Иванов","dfdf",pass,"someone","1414"));
            ivan.setNeedToChangePassword(false);
            repository.save(new User("88005553535",role,"Иван", "Иванов","0",pass,"someone","1414"));
            repository.save(new User("88005553535",role1,"Иван", "Иванов","1",pass,"someone","1414"));
            repository.save(new User("88005553535",role2,"Иван", "Иванов","2",pass,"someone","1414"));
            repository.save(new User("88005553535",role3,"Иван", "Иванов","3",pass,"someone","1414"));
            // выборка всех кастомеров
        };
    }
}
