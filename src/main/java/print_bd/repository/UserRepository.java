package print_bd.repository;

/**
 * Created by sereo_000 on 16.09.2016.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import print_bd.entity.User;
import print_bd.entity.UserRole;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    List<User> findByLastNameStartsWithIgnoreCase(String lastName);
    List<User> findByContact(String contact);
    List<User> findByUserRole(UserRole userRole);
    List<User> findByEmail(String email);
    List<User> findByEmailAndPasswordHash(String email,String passwordHash);
    List<User> findByJobOfUser(String jobOfUser);
    List<User> findByPassportNumber(String passportNumber);
}
