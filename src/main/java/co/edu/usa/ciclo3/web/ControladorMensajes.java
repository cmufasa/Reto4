package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Message;
import co.edu.usa.ciclo3.service.CabinService;
import co.edu.usa.ciclo3.service.ClientService;
import co.edu.usa.ciclo3.service.MessageService;
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
public class ControladorMensajes {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CabinService cabinService;

    @GetMapping("/mensaje")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var messages = messageService.getList();
        var clients = clientService.getList();
        var cabins = cabinService.getList();
        log.info("Ejecutando el controlador Spring MVC para mensajes");
        model.addAttribute("messages", messages);
        model.addAttribute("clients", clients);
        model.addAttribute("cabins", cabins);
        return "mensajes";
    }

    @GetMapping("/mensaje/addmessage")
    public String agregar(Message message) {
        return "modifyMessage";
    }

    @PostMapping("/mensaje/savemessage")
    public String guardar(@Valid Message message, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyMessage";
        }
        messageService.save(message);
        return "redirect:/mensaje";
    }

    @GetMapping("/mensaje/searchmessage/{idMessage}")
    public String buscar(Message message, Model model) {
        var clients = clientService.getList();
        var cabins = cabinService.getList();
        message = messageService.getById(message.getIdMessage());
        model.addAttribute("message", message);
        model.addAttribute("clients", clients);
        model.addAttribute("cabins", cabins);
        return "modifyMessage";
    }

    @PostMapping("/mensaje/updatemessage")
    public String editar(@Valid Message message, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyMessage";
        }
        messageService.update(message);
        return "redirect:/mensaje";
    }

    @GetMapping("/mensaje/deletemessage/{idMessage}")
    public String eliminar(Message message) {
        messageService.delete(message.getIdMessage());
        return "redirect:/mensaje";
    }
}
