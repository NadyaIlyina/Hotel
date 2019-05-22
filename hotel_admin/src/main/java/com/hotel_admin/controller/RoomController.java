package com.hotel_admin.controller;

import com.hotel_admin.model.Room;
import com.hotel_admin.service.FileService;
import com.hotel_admin.service.RoomsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RoomController {

    private RoomsService service;
    private FileService fileService;

    public RoomController(RoomsService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @GetMapping("/rooms")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Room find = service.findById(id);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/rooms")
    public ResponseEntity save(@RequestBody Room room) {
        Room saved = service.save(room);
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
