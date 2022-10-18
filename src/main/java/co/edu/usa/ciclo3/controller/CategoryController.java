package co.edu.usa.ciclo3.controller;

import co.edu.usa.ciclo3.entity.Category;
import co.edu.usa.ciclo3.service.CategoryService;
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
@RequestMapping(value = "api/Category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategoryList(){
        return categoryService.getList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCategoryById(@PathVariable("id") Long id){
        return categoryService.getById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveCategory(@RequestBody Category category){
       categoryService.save(category);
       return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateCategory(@RequestBody Category category){
        categoryService.update(category);
        return ResponseEntity.status(201).build();
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteCategory(@PathVariable("id") Long id){
       categoryService.delete(id);
       return ResponseEntity.status(204).build();
    }
    
}
