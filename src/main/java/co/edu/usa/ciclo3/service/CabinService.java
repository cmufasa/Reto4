package co.edu.usa.ciclo3.service;

import co.edu.usa.ciclo3.entity.Cabin;
import co.edu.usa.ciclo3.repository.CabinRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CabinService {
    
    @Autowired
    private CabinRepository cabinRepository;
    
    @Transactional(readOnly = true)
    public List<Cabin> getList() {
        return (List<Cabin>) cabinRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Cabin getById(Long id) {
        return cabinRepository.findById(id).get();
    }
    
    @Transactional
    public Cabin save(Cabin cabin) {
        return cabinRepository.save(cabin);
    }
    
    @Transactional
    public Cabin update(Cabin cabin) {
        if (cabin.getId() != null) {
            Optional<Cabin> cabinId = cabinRepository.findById(cabin.getId());
            
            if (!cabinId.isEmpty()) {
                Cabin cabinBd = cabinId.get();
                
                cabinBd.setBrand(cabin.getBrand());
                cabinBd.setRooms(cabin.getRooms());
                cabinBd.setName(cabin.getName());
                cabinBd.setDescription(cabin.getDescription());
                
                return cabinRepository.save(cabinBd);
                
            } else {
                
                return cabin;
            }
        }
        return cabin;
    }
    
    @Transactional
    public void delete(Long id) {
        cabinRepository.deleteById(id);
    }
    
}
