package com.hotel_admin.controller;

import com.hotel_admin.model.Staff;
import com.hotel_admin.service.FileService;
import com.hotel_admin.service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api")

public class StaffController {

    private StaffService service;
    private FileService fileService;

    public StaffController(StaffService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @GetMapping("/staff")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Staff find = service.findById(id);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/staff")
    public ResponseEntity save(@RequestBody Staff staff) {
        Staff saved = service.save(staff);
        /*if (staff.getId() == admin.getId() && !staff.getLogin().equals(admin.getLogin())){
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.ok().build();
        }*/
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity delete(@PathVariable int id, Principal principal) {
        Staff staff = service.findByLastName(principal.getName());
        if (staff == null)
            return ResponseEntity.badRequest().build();
        if (staff.getId() == id){
            return ResponseEntity.unprocessableEntity().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
