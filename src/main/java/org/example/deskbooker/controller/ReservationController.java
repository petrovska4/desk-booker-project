package org.example.deskbooker.controller;

import org.example.deskbooker.model.Reservation;
import org.example.deskbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/get-by-id")
    public Reservation getById(@RequestParam String id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/get-all")
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/create")
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        reservationService.deleteReservation(id);
    }
}
