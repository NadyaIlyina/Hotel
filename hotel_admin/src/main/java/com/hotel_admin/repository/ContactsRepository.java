package com.hotel_admin.repository;

import com.hotel_admin.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
    List<Contacts> findByIdIn(Collection<Integer> ids);
}

