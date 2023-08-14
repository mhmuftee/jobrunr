package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TestService {

    @Autowired
    NewEntityRepository repository;

    Stream<IdProjection> find(){
        return repository.fetchAll();
    }

    public void del(Long id){
        System.out.println("service: " + id);
        repository.deleteById(id);
    }

}
