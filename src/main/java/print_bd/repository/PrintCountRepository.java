package print_bd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import print_bd.entity.PrintCount;

import java.util.List;

/**
 * Created by sereo_000 on 06.10.2016.
 */
@Repository
public interface PrintCountRepository extends JpaRepository<PrintCount,Integer> {
    List<PrintCount> findByPermissiblePrints(Integer permissiblePrints);
}
