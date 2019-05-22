package com.hotel_admin.service;

import com.hotel_admin.model.Contacts;
import com.hotel_admin.repository.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContactsService {
    private ContactsRepository repository;

    public ContactsService(ContactsRepository repository) {
        this.repository = repository;
    }

    public Collection<Contacts> findAll(){
        return repository.findAll();
    }

    public Collection<Contacts> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public Contacts findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Contacts save(Contacts contacts){
        return repository.save(contacts);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
