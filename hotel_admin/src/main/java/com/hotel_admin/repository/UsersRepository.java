package com.hotel_admin.repository;

import com.hotel_admin.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UsersRepository extends JpaRepository <Users, Integer> {

    List <Users> findByIdIn(Collection<Integer> ids);
    Users findByLogin(String login);
}
