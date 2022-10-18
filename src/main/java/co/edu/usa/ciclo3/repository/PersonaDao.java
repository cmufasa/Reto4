package co.edu.usa.ciclo3.repository;

import co.edu.usa.ciclo3.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
