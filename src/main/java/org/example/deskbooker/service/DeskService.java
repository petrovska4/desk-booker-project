package org.example.deskbooker.service;

import org.example.deskbooker.model.Desk;
import org.example.deskbooker.model.Office;
import org.example.deskbooker.repository.DeskRepository;
import org.example.deskbooker.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeskService {
    private final DeskRepository deskRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    public DeskService(DeskRepository deskRepository, OfficeRepository officeRepository) {
        this.deskRepository = deskRepository;
        this.officeRepository = officeRepository;
    }

    public Desk getDesk(String id) {
        return deskRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Desk with ID " + id + " not found"));
    }

    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    public List<Desk> getAllDesksByDateRange(LocalDate startDate, LocalDate endDate) {
        return deskRepository.findAvailableDesks(startDate, endDate);
    }

    public Desk addDesk(Desk desk) {
        return validateDesk(desk);
    }

    public Desk updateDesk(Desk desk) {
        deskRepository.findById(desk.getId())
                .orElseThrow(() -> new RuntimeException("Desk not found with id: " + desk.getId()));

        return validateDesk(desk);
    }

    public void deleteDesk(String id) {
        long deskId = Long.parseLong(id);
        deskRepository.findById(deskId)
                        .orElseThrow(() -> new IllegalArgumentException("Desk with ID " + id + " not found"));
        deskRepository.deleteById(Long.valueOf(id));
    }

    private Desk validateDesk(Desk desk) {
        if (desk.getOffice() != null && desk.getOffice().getId() != null) {
            Office office = officeRepository.findById(desk.getOffice().getId())
                    .orElseThrow(() -> new RuntimeException("Office not found with id: " + desk.getOffice().getId()));
            desk.setOffice(office);
        } else {
            desk.setOffice(null);
        }

        return deskRepository.save(desk);
    }
}
