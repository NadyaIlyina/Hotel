package com.hotel_admin.repository;

import com.hotel_admin.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByIdIn(Collection<Integer> ids);

    @Query(value = "SELECT COUNT(*) FROM staff WHERE id_position = ?1", nativeQuery = true)
    int getStaffCountByPositionId(int id);

    Staff findByLastName(String lastName);
}
