package com.hotel_admin.service;

import com.hotel_admin.model.News;
import com.hotel_admin.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NewsService {

    private NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public Collection<News> findAll(){
        return repository.findAll();
    }

    public Collection<News> findAll(Collection<Integer> ids){
        return repository.findByIdIn(ids);
    }

    public News findById(int id){
        return repository.findById(id).orElse(null);
    }

    public News save(News news){
        return repository.save(news);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
