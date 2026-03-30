package com.app.quantitymeasurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository 
        extends JpaRepository<QuantityMeasurementEntity, Long> {
}