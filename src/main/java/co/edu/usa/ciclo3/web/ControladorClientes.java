package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Client;
import co.edu.usa.ciclo3.service.ClientService;
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
public class ControladorClientes {

    @Autowired
    private ClientService clientService;

    @GetMapping("/cliente")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var clients = clientService.getList();
        log.info("Ejecutando el controlador Spring MVC para clientes");
        model.addAttribute("clients", clients);
        return "clientes";
    }

    @GetMapping("/cliente/addclient")
    public String agregar(Client client) {
        return "modifyClient";
    }

    @PostMapping("/cliente/saveclient")
    public String guardar(@Valid Client client, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyClient";
        }
        clientService.save(client);
        return "redirect:/cliente";
    }

    @GetMapping("/cliente/searchclient/{idClient}")
    public String buscar(Client client, Model model) {
        client = clientService.getById(client.getIdClient());
        model.addAttribute("client", client);
        return "modifyClient";
    }

    @PostMapping("/cliente/updateclient")
    public String editar(@Valid Client client, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyClient";
        }
        clientService.update(client);
        return "redirect:/cliente";
    }

    @GetMapping("/cliente/deleteclient/{idClient}")
    public String eliminar(Client client) {
        clientService.delete(client.getIdClient());
        return "redirect:/cliente";
    }
}
