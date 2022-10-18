package co.edu.usa.ciclo3.controller;

import co.edu.usa.ciclo3.entity.Cabin;
import co.edu.usa.ciclo3.service.CabinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/Cabin")
public class CabinController {
    
    @Autowired
    private CabinService cabinService;
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Cabin> getCabinList(){
        return cabinService.getList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cabin getCabinById(@PathVariable("id") Long id){
        return cabinService.getById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveCabin(@RequestBody Cabin cabin){
       cabinService.save(cabin);
       return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateCabin(@RequestBody Cabin cabin){
        cabinService.update(cabin); 
        return ResponseEntity.status(201).build();
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteCabin(@PathVariable("id") Long id){
       cabinService.delete(id);
       return ResponseEntity.status(204).build();
    }
    
}
