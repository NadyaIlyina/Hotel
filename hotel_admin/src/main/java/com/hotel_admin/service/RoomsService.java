package com.hotel_admin.service;

import com.hotel_admin.model.Room;
import com.hotel_admin.repository.RoomsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomsService {

    private RoomsRepository repository;

    public RoomsService(RoomsRepository usersRepository){
        this.repository =usersRepository;
    }
    public Collection<Room> findAll(){
        return repository.findAll();
    }

    public Collection<Room> findAll(Sort sort){
        return StreamSupport.stream(repository.findAll(sort).spliterator(),false).collect(Collectors.toList());
    }

    public Collection<Room> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public Room findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Collection<Room> findAllByCategoryId(int id){
        return  repository.findByCategoryRooms(id);
    }

    public Room save(Room room){
        return repository.save(room);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

}
