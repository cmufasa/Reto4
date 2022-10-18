package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Cabin;
import co.edu.usa.ciclo3.service.impl.ReservationServiceImpl;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var status = reservationServiceImpl.getStatusReports();
        var topclients = reservationServiceImpl.getTopClients();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("completed", status.getCompleted());
        model.addAttribute("cancelled", status.getCancelled());
        model.addAttribute("topclients", topclients.get(0).getTotal());
        return "index";
    }

    @GetMapping("/topclients/{dI}/{dO}")
    public String buscar(String dI, String dO, Model model) throws ParseException {

        var periods = reservationServiceImpl.getReservationPeriod(dI, dO);
        model.addAttribute("periods", periods);
        return "periodos";
    }
}
