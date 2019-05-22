package com.hotel_admin.repository;

import com.hotel_admin.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    List<Gallery> findByIdIn(Collection<Integer> ids);
}

