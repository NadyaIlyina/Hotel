package com.hotel_admin.repository;

import com.hotel_admin.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByIdIn(Collection<Integer> ids);
    Client findByLastName(String lastName);
}
