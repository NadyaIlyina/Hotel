package com.hotel_admin.service;

import com.hotel_admin.model.Regestration;
import com.hotel_admin.repository.RegestartionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RegestrationService {
    private RegestartionRepository repository;

    public RegestrationService(RegestartionRepository repository) {
        this.repository = repository;
    }

    public Collection<Regestration> findAll() {
        return repository.findAll();
    }

    public Collection<Regestration> findAll(Collection<Integer> ids) {
        return repository.findByIdIn(ids);
    }

    public Regestration findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Regestration save(Regestration regestration) {
        return repository.save(regestration);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Collection<Regestration> findAllByStaffId(int id) {
        return repository.findByStaff(id);
    }

    public Collection<Regestration> findAllByClientId(int id) {
        return repository.findByClient(id);
    }

    public Collection<Regestration> findAllByRoomId(int id) {
        return repository.findByRoom(id);
    }
}
