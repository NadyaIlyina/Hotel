package com.hotel_admin.repository;

import com.hotel_admin.model.CategoryRooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRoomsRepository extends JpaRepository<CategoryRooms, Integer> {
    List<CategoryRooms> findByIdIn(Collection<Integer> ids);
}
