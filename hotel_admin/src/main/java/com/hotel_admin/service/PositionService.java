package com.hotel_admin.service;

import com.hotel_admin.model.Position;
import com.hotel_admin.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PositionService {
    private PositionRepository repository;

    public PositionService(PositionRepository  repository) {
        this.repository = repository;
    }

    public Collection<Position> findAll(){
        return repository.findAll();
    }

    public Collection<Position> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public Position findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Position save(Position category){
        return repository.save(category);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
