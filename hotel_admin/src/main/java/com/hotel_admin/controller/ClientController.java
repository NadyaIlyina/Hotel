package com.hotel_admin.controller;

import com.hotel_admin.model.Client;
import com.hotel_admin.service.ClientService;
import com.hotel_admin.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api")

public class ClientController {
    private ClientService service;
    private FileService fileService;

    public ClientController(ClientService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @GetMapping("/client")
    public ResponseEntity findAll(@RequestParam(name = "ids", required = false) Set<Integer> ids) {
        if (ids != null && !ids.isEmpty())
            return ResponseEntity.ok(service.findAll(ids));
        else
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Client find = service.findById(id);
        if (find == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/client")
    public ResponseEntity save(@RequestBody Client client) {
        Client saved = service.save(client);
        /*if (client.getId() == admin.getId() && !client.getLogin().equals(admin.getLogin())){
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.ok().build();
        }*/
        if (saved == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity delete(@PathVariable int id, Principal principal) {
        Client client = service.findByLastName(principal.getName());
        if (client == null)
            return ResponseEntity.badRequest().build();
        if (client.getId() == id){
            return ResponseEntity.unprocessableEntity().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
