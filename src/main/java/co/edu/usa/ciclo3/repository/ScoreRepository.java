package co.edu.usa.ciclo3.repository;

import co.edu.usa.ciclo3.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>{
    
}
