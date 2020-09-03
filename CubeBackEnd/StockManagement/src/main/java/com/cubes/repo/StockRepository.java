package com.cubes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cubes.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
