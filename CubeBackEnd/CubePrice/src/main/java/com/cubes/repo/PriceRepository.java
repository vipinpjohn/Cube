package com.cubes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cubes.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
