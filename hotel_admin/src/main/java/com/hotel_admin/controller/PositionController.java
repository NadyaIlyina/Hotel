package com.hotel_admin.controller;

import com.hotel_admin.model.Position;
import com.hotel_admin.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PositionController {

    private PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping("/positions")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Position find = service.findById(id);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/positions")
    public ResponseEntity save(@RequestBody Position positions) {
        Position saved = service.save(positions);
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/positions/{id}")
    public ResponseEntity delete(@PathVariable int id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}