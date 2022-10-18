package co.edu.usa.ciclo3.service.impl;

import co.edu.usa.ciclo3.custom.CountClient;
import co.edu.usa.ciclo3.custom.DescriptionAmount;
import co.edu.usa.ciclo3.entity.Reservation;
import co.edu.usa.ciclo3.service.ReservationService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl {

    @Autowired
    private ReservationService reservationService;

    public List<CountClient> getTopClients() {
        return reservationService.getTopClients();
    }

    public DescriptionAmount getStatusReports() {
        DescriptionAmount descriptionAmount = new DescriptionAmount();
        
        List<Reservation> completed = reservationService.getReservationsByStatus("completed");
        List<Reservation> cancelled = reservationService.getReservationsByStatus("cancelled");
        
        if (completed.isEmpty() && cancelled.isEmpty()) {
            descriptionAmount = new DescriptionAmount(0,0);
        } else {
            descriptionAmount = new DescriptionAmount(completed.size(),cancelled.size());
        }
        return descriptionAmount;
    }

    public List<Reservation> getReservationPeriod(String dI, String dO) throws java.text.ParseException {
        
        SimpleDateFormat parserDate = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dateIn = new Date();
        Date dateOut = new Date();
        
        dateIn = parserDate.parse(dI);
        dateOut = parserDate.parse(dO);
        
        if (dateIn.before(dateOut)) {
            return reservationService.getReservationsPeriod(dateIn, dateOut);
        }else{
            return new ArrayList<>();
        }
    }
}
