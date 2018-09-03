package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TravelService;

@Repository
public interface TravelServiceRepository extends CrudRepository<TravelService, Integer> {

}
