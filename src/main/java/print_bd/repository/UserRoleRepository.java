package print_bd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import print_bd.entity.PrintCount;
import print_bd.entity.UserRole;

import java.util.List;

/**
 * Created by sereo_000 on 16.09.2016.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>{
    List<UserRole> findByAdmin(boolean admin);
    List<UserRole> findByPrintCount(List<PrintCount> printCounts);
}
