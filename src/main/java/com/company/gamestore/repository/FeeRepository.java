package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FeeRepository extends JpaRepository<Fee, Integer> {
    Optional<Fee> findByProductType(String productType);
    Integer deleteByProductType(String productType);
}
