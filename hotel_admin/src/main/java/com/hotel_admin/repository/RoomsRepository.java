package com.hotel_admin.repository;

import com.hotel_admin.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface RoomsRepository  extends PagingAndSortingRepository<Room,Integer> {

    List<Room> findAll();
    List<Room> findByIdIn(Collection<Integer> ids);
    List<Room> findByCategoryRooms(int id);
}
