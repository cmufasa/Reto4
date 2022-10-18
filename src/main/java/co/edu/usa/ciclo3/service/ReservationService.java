package co.edu.usa.ciclo3.service;

import co.edu.usa.ciclo3.custom.CountClient;
import co.edu.usa.ciclo3.entity.Client;
import co.edu.usa.ciclo3.entity.Reservation;
import co.edu.usa.ciclo3.repository.ReservationRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<Reservation> getList() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reservation getById(Long id) {
        return reservationRepository.findById(id).get();
    }

    @Transactional
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservationId = reservationRepository.findById(reservation.getIdReservation());

            if (!reservationId.isEmpty()) {
                Reservation reservationBd = reservationId.get();

                reservationBd.setStartDate(reservation.getStartDate());
                reservationBd.setDevolutionDate(reservation.getDevolutionDate());
                reservationBd.setStatus(reservation.getStatus());
                reservationBd.setScore(reservation.getScore());
                reservationBd.setCabin(reservation.getCabin());
                reservationBd.setClient(reservation.getClient());

                return reservationRepository.save(reservationBd);

            } else {

                return reservation;
            }
        }
        return reservation;
    }

    @Transactional
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationsPeriod(Date dateIn, Date dateOut) {
        return reservationRepository.findAllByStartDateAfterAndStartDateBefore(dateIn, dateOut);
    }

    public List<CountClient> getTopClients() {
        List<CountClient> countClients = new ArrayList<>();

        List<Object[]> reportList = reservationRepository.countTotalReservationByClient();

        if (reportList.isEmpty()) {
            countClients.add(new CountClient((Long) 0L, (Client) new Client()));
        } else {
            for (int i = 0; i < reportList.size(); i++) {
                countClients.add(new CountClient((Long) reportList.get(i)[1], (Client) reportList.get(i)[0]));
            }
        }

        return countClients;
    }
}
