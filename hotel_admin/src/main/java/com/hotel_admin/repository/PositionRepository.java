package com.hotel_admin.repository;

import com.hotel_admin.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findByIdIn(Collection<Integer> ids);
}

