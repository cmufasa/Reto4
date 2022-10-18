package co.edu.usa.ciclo3.web;

import co.edu.usa.ciclo3.entity.Reservation;
import co.edu.usa.ciclo3.service.CabinService;
import co.edu.usa.ciclo3.service.ClientService;
import co.edu.usa.ciclo3.service.ReservationService;
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
public class ControladorReservas {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CabinService cabinService;

    @GetMapping("/reserva")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var reservations = reservationService.getList();
        var clients = clientService.getList();
        var cabins = cabinService.getList();
        log.info("Ejecutando el controlador Spring MVC para reservas");
        model.addAttribute("reservations", reservations);
        model.addAttribute("clients", clients);
        model.addAttribute("cabins", cabins);
        return "reservas";
    }

    @GetMapping("/reserva/addreservation")
    public String agregar(Reservation reservation) {
        return "modifyReservation";
    }

    @PostMapping("/reserva/savereservation")
    public String guardar(@Valid Reservation reservation, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyReservation";
        }
        reservationService.save(reservation);
        return "redirect:/reserva";
    }

    @GetMapping("/reserva/searchreservation/{idReservation}")
    public String buscar(Reservation reservation, Model model) {
        var clients = clientService.getList();
        var cabins = cabinService.getList();
        reservation = reservationService.getById(reservation.getIdReservation());
        model.addAttribute("reservation", reservation);
        model.addAttribute("clients", clients);
        model.addAttribute("cabins", cabins);
        return "modifyReservation";
    }

    @PostMapping("/reserva/updatereservation")
    public String editar(@Valid Reservation reservation, Errors errores) {
        if (errores.hasErrors()) {
            return "modifyReservation";
        }
        reservationService.update(reservation);
        return "redirect:/reserva";
    }

    @GetMapping("/reserva/deletereservation/{idReservation}")
    public String eliminar(Reservation reservation) {
        reservationService.delete(reservation.getIdReservation());
        return "redirect:/reserva";
    }
}
