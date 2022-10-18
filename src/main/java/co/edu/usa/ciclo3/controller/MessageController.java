package co.edu.usa.ciclo3.controller;

import co.edu.usa.ciclo3.entity.Message;
import co.edu.usa.ciclo3.service.MessageService;
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
@RequestMapping(value = "api/Message")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessageList(){
        return messageService.getList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message getMessageById(@PathVariable("id") Long id){
        return messageService.getById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveMessage(@RequestBody Message message){
       messageService.save(message);
       return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateMessage(@RequestBody Message message){
        messageService.update(message);
        return ResponseEntity.status(201).build();        
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteMessage(@PathVariable("id") Long id){
       messageService.delete(id);
       return ResponseEntity.status(204).build();
    }
    
}
