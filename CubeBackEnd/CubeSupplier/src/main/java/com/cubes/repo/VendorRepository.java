package com.cubes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cubes.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
