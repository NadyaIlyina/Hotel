package com.hotel_admin.service;

import com.hotel_admin.model.Staff;
import com.hotel_admin.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StaffService {
    private StaffRepository repository;

    public StaffService(StaffRepository staffRepository){
        this.repository =staffRepository;
    }
    public Collection<Staff> findAll(){
        return repository.findAll();
    }

    public Collection<Staff> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public Staff findByLastName(String lastName){
        return repository.findByLastName(lastName);
    }
    public Staff findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Staff save(Staff staff){
        return repository.save(staff);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

}
