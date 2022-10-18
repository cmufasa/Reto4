package co.edu.usa.ciclo3.controller;

import co.edu.usa.ciclo3.custom.CountClient;
import co.edu.usa.ciclo3.custom.DescriptionAmount;
import co.edu.usa.ciclo3.entity.Reservation;
import co.edu.usa.ciclo3.entity.Reservation;
import co.edu.usa.ciclo3.service.ReservationService;
import co.edu.usa.ciclo3.service.impl.ReservationServiceImpl;
import java.text.ParseException;
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
@RequestMapping(value = "api/Reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
        @Autowired
    private ReservationServiceImpl reservationServiceImpl;
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationList(){
        return reservationService.getList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservationById(@PathVariable("id") Long id){
        return reservationService.getById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveReservation(@RequestBody Reservation reservation){
       reservationService.save(reservation);
       return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateReservation(@RequestBody Reservation reservation){
        reservationService.update(reservation);
        return ResponseEntity.status(201).build();
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteReservation(@PathVariable("id") Long id){
       reservationService.delete(id);
       return ResponseEntity.status(204).build();
    }
    
    @GetMapping("/report-status")
    public DescriptionAmount getReservationStatus(){
        return reservationServiceImpl.getStatusReports();
    }
    
    @GetMapping("/report-clients")
    public List<CountClient> getCountClients(){
        return reservationServiceImpl.getTopClients();
    }
    
    @GetMapping("/report-dates/{dateIn}/{dateOut}")
    public List<Reservation> getDatesReport(@PathVariable("dateIn") String dateIn, @PathVariable("dateOut") String dateOut) throws ParseException{
        return reservationServiceImpl.getReservationPeriod(dateIn, dateOut);
    }
}
