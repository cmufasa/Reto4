package co.edu.usa.ciclo3.repository;

import co.edu.usa.ciclo3.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
