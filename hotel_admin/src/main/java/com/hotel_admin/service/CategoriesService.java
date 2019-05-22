package com.hotel_admin.service;

import com.hotel_admin.model.CategoryRooms;
import com.hotel_admin.repository.CategoryRoomsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoriesService {

    private CategoryRoomsRepository repository;

    public CategoriesService(CategoryRoomsRepository  repository) {
        this.repository = repository;
    }

    public Collection<CategoryRooms> findAll(){
        return repository.findAll();
    }

    public Collection<CategoryRooms> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public CategoryRooms findById(int id){
        return repository.findById(id).orElse(null);
    }

    public CategoryRooms save(CategoryRooms category){
        return repository.save(category);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
