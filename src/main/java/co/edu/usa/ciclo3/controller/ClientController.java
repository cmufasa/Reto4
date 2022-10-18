package co.edu.usa.ciclo3.controller;

import co.edu.usa.ciclo3.entity.Client;
import co.edu.usa.ciclo3.service.ClientService;
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
@RequestMapping(value = "api/Client")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClientList(){
        return clientService.getList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClientById(@PathVariable("id") Long id){
        return clientService.getById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveClient(@RequestBody Client client){
       clientService.save(client);
       return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateClient(@RequestBody Client client){
        clientService.update(client); 
        return ResponseEntity.status(201).build();
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteClient(@PathVariable("id") Long id){
       clientService.delete(id);
       return ResponseEntity.status(204).build();
    }
    
}
