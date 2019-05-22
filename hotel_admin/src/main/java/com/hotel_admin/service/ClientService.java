package com.hotel_admin.service;

import com.hotel_admin.model.Client;
import com.hotel_admin.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientService {

    private ClientRepository repository;

    public ClientService(ClientRepository clientRepository){
        this.repository =clientRepository;
    }
    public Collection<Client> findAll(){
        return repository.findAll();
    }

    public Collection<Client> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }
    public Client findByLastName(String lastName){
        return repository.findByLastName(lastName);
    }

    public Client findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Client save(Client user){
        return repository.save(user);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

}
