package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Cabin;
import co.edu.usa.ciclo3.service.CabinService;
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
public class ControladorCabanas {

    @Autowired
    private CabinService cabinService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/cabana")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var categorys = categoryService.getList();
        var cabins = cabinService.getList();
        log.info("Ejecutando el controlador Spring MVC para cabanas");
        model.addAttribute("cabins", cabins);
        model.addAttribute("categorys", categorys);
        return "cabanas";
    }

    @GetMapping("/cabana/addcabin")
    public String agregar(Cabin cabin) {
        return "modifyCabin";
    }

    @PostMapping("/cabana/savecabin")
    public String guardar(@Valid Cabin cabin, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyCabin";
        }
        cabinService.save(cabin);
        return "redirect:/cabana";
    }

    @GetMapping("/cabana/searchcabin/{id}")
    public String buscar(Cabin cabin, Model model) {
        var categorys = categoryService.getList();
        cabin = cabinService.getById(cabin.getId());
        model.addAttribute("cabin", cabin);
        model.addAttribute("categorys", categorys);
        return "modifyCabin";
    }

    @PostMapping("/cabana/updatecabin")
    public String editar(@Valid Cabin cabin, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyCabin";
        }
        cabinService.update(cabin);
        return "redirect:/cabana";
    }

    @GetMapping("/cabana/deletecabin/{id}")
    public String eliminar(Cabin cabin) {
        cabinService.delete(cabin.getId());
        return "redirect:/cabana";
    }
}
