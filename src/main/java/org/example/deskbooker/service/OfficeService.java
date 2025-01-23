package org.example.deskbooker.service;

import org.example.deskbooker.model.Office;
import org.example.deskbooker.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {
    OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Office getOffice(String id) {
        return officeRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Desk with ID " + id + " not found"));
    }

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    public Office addOffice(Office office) {
        return officeRepository.save(office);
    }

    public Office updateOffice(Office office) {
        return officeRepository.save(office);
    }

    public void deleteOffice(String id) {
        officeRepository.deleteById(Long.valueOf(id));
    }
}
