package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {

    @Query(value = "select * from new_entity", nativeQuery = true)
    Stream<IdProjection> fetchAll();

}