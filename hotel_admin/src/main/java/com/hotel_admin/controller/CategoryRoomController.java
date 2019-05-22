package com.hotel_admin.controller;


import com.hotel_admin.model.CategoryRooms;
import com.hotel_admin.service.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CategoryRoomController {

    private CategoriesService service;

    public CategoryRoomController(CategoriesService service) {
        this.service = service;
    }

    @GetMapping("/category_rooms")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/category_rooms/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        CategoryRooms find = service.findById(id);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/category_rooms")
    public ResponseEntity save(@RequestBody CategoryRooms category) {
        CategoryRooms saved = service.save(category);
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/category_rooms/{id}")
    public ResponseEntity delete(@PathVariable int id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
