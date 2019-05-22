package com.hotel_admin.service;

import com.hotel_admin.model.Gallery;
import com.hotel_admin.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GalleryService {
    private GalleryRepository repository;

    public GalleryService(GalleryRepository repository) {
        this.repository = repository;
    }

    public Collection<Gallery> findAll(){
        return repository.findAll();
    }

    public Collection<Gallery> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public Gallery findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Gallery save(Gallery gallery){
        return repository.save(gallery);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
