package com.hotel_admin.controller;

import com.hotel_admin.model.Regestration;
import com.hotel_admin.service.RegestrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api")

public class RegestrationController {

    private RegestrationService service;

    public RegestrationController(RegestrationService service) {
        this.service = service;
    }

    @GetMapping("/regestration")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/regestration/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Regestration find = service.findById(id);
        Date start = find.getCheck_in();
        Date end = find.getCheck_out();
        Double cost = find.getRoom().getCategoryRooms().getCost_per_day();
        int diffInDays = (int)( (end.getTime() - start.getTime())
                / (1000 * 60 * 60 * 24) );
        double totalSum = diffInDays*cost;
        find.setTotalSum(totalSum);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/regestration")
    public ResponseEntity save(@RequestBody Regestration regestration) {
        Regestration saved = service.save(regestration);
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/regestration/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
