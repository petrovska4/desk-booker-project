package org.example.deskbooker.controller;

import org.example.deskbooker.model.Desk;
import org.example.deskbooker.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/desk")
public class DeskController {
    private DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping("/get-by-id")
    public Desk getById(@RequestParam String id) {
        return deskService.getDesk(id);
    }

    @GetMapping("/get-all")
    public List<Desk> getAll() {
        return deskService.getAllDesks();
    }

    @GetMapping("/get-all-by-date-range")
    public List<Desk> getAllByDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return deskService.getAllDesksByDateRange(from, to);
    }

    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PostMapping("/create")
    public Desk create(@RequestBody Desk desk) {
        return deskService.addDesk(desk);
    }

    @PutMapping("/update")
    public Desk update(@RequestBody Desk desk) {
        return deskService.updateDesk(desk);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        deskService.deleteDesk(id);
    }
}
