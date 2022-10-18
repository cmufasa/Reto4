package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Score;
import co.edu.usa.ciclo3.service.ScoreService;
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
public class ControladorCalificaciones {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/califica")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var scores = scoreService.getList();
        log.info("Ejecutando el controlador Spring MVC para calificaciones");
        model.addAttribute("scores", scores);
        return "calificaciones";
    }

    @GetMapping("/califica/addscore")
    public String agregar(Score score) {
        return "modifyScore";
    }

    @PostMapping("/califica/savescore")
    public String guardar(@Valid Score score, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyScore";
        }
        scoreService.save(score);
        return "redirect:/califica";
    }

    @GetMapping("/califica/searchscore/{id}")
    public String buscar(Score score, Model model) {
        score = scoreService.getById(score.getId());
        model.addAttribute("score", score);
        return "modifyScore";
    }

    @PostMapping("/califica/updatescore")
    public String editar(@Valid Score score, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyScore";
        }
        scoreService.update(score);
        return "redirect:/califica";
    }

    @GetMapping("/califica/deletescore/{id}")
    public String eliminar(Score score) {
        scoreService.delete(score.getId());
        return "redirect:/califica";
    }
}
