package co.edu.usa.ciclo3.repository;

import co.edu.usa.ciclo3.entity.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long>{
    
}
