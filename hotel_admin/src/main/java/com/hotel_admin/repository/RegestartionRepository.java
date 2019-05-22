package com.hotel_admin.repository;

import com.hotel_admin.model.Regestration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RegestartionRepository extends JpaRepository<Regestration, Integer> {

    List<Regestration> findByIdIn(Collection<Integer> ids);
    List<Regestration> findByClient(int id);
    List<Regestration> findByRoom(int id);
    List<Regestration> findByStaff(int id);
}
