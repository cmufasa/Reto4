package co.edu.usa.ciclo3.controller;

import co.edu.usa.ciclo3.entity.Admin;
import co.edu.usa.ciclo3.service.AdminService;
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
@RequestMapping(value = "api/Admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAdminList(){
        return adminService.getList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin getAdminById(@PathVariable("id") Long id){
        return adminService.getById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveAdmin(@RequestBody Admin admin){
       adminService.save(admin);
       return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateAdmin(@RequestBody Admin admin){
        adminService.update(admin);
        return ResponseEntity.status(201).build();        
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteAdmin(@PathVariable("id") Long id){
       adminService.delete(id);
       return ResponseEntity.status(204).build();
    }
    
}
