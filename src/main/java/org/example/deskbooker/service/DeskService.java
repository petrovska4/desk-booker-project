package org.example.deskbooker.service;

import org.example.deskbooker.model.Desk;
import org.example.deskbooker.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskService {
    DeskRepository deskRepository;

    @Autowired
    public DeskService(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    public Desk getDesk(String id) {
        return deskRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Desk with ID " + id + " not found"));
    }

    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    public Desk addDesk(Desk desk) {
        return deskRepository.save(desk);
    }

    public Desk updateDesk(Desk desk) {
        return deskRepository.save(desk);
    }

    public void deleteDesk(String id) {
        deskRepository.deleteById(Long.valueOf(id));
    }
}
