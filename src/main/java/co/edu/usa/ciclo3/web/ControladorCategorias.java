package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Category;
import co.edu.usa.ciclo3.service.CategoryService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorCategorias {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categoria")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var categorys = categoryService.getList();
        log.info("Ejecutando el controlador Spring MVC para categorias");
        model.addAttribute("categorys", categorys);
        return "categorias";
    }

    @GetMapping("/categoria/addcategory")
    public String agregar(Category category) {
        return "modifyCategory";
    }

    @PostMapping("/categoria/savecategory")
    public String guardar(@Valid Category category, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyCategory";
        }
        categoryService.save(category);
        return "redirect:/categoria";
    }

    @GetMapping("/categoria/searchcategory/{id}")
    public String buscar(Category category, Model model) {
        category = categoryService.getById(category.getId());
        model.addAttribute("category", category);
        return "modifyCategory";
    }

    @PostMapping("/categoria/updatecategory")
    public String editar(@Valid Category category, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyCategory";
        }
        categoryService.update(category);
        return "redirect:/categoria";
    }

    @GetMapping("/categoria/deletecategory/{id}")
    public String eliminar(Category category) {
        categoryService.delete(category.getId());
        return "redirect:/categoria";
    }
}
