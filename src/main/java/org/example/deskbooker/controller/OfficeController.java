package org.example.deskbooker.controller;

import org.example.deskbooker.model.Desk;
import org.example.deskbooker.model.Office;
import org.example.deskbooker.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeController {
    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/get-by-id")
    public Office getById(@RequestParam String id) {
        return officeService.getOffice(id);
    }

    @GetMapping("/get-all")
    public List<Office> getAll() {
        return officeService.getAllOffices();
    }

    @PostMapping("/create")
    public Office create(@RequestBody Office office) {
        return officeService.addOffice(office);
    }

    @PutMapping("/update")
    public Office update(@RequestBody Office office) {
        return officeService.updateOffice(office);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        officeService.deleteOffice(id);
    }
}
