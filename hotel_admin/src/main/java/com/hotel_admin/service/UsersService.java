package com.hotel_admin.service;

import com.hotel_admin.model.Users;
import com.hotel_admin.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsersService {
    private UsersRepository repository;

    public UsersService(UsersRepository usersRepository){
        this.repository =usersRepository;
    }
    public Collection<Users> findAll(){
        return repository.findAll();
    }

    public Collection<Users> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }
    public Users findByLogin(String login){
        return repository.findByLogin(login);
    }

    public Users findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Users save(Users users){
        return repository.save(users);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
