package com.hotel_admin.controller;

import com.hotel_admin.model.Users;
import com.hotel_admin.service.FileService;
import com.hotel_admin.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.DELETE}, allowedHeaders = "*", origins = "*")
public class UsersController {

    private UsersService service;
    private FileService fileService;

    public UsersController(UsersService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @GetMapping("/users")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Users find = service.findById(id);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/users")
    public ResponseEntity save(@RequestBody Users users, Principal principal) {
        Users admin = service.findByLogin("admin");
        if (admin == null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.badRequest().build();
        }
        Users saved = service.save(users);
        if (users.getId() == admin.getId() && !users.getLogin().equals(admin.getLogin())){
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.ok().build();
        }
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable int id, Principal principal) {
        Users users = service.findByLogin(principal.getName());
        if (users == null)
            return ResponseEntity.badRequest().build();
        if (users.getId() == id){
            return ResponseEntity.unprocessableEntity().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
