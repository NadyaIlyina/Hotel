package com.hotel_admin.repository;

import com.hotel_admin.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByIdIn(Collection<Integer> ids);

}
