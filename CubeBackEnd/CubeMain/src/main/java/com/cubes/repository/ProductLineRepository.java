package com.cubes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cubes.model.ProductLine;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, String> {

}
