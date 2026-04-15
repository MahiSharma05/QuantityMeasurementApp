package com.quantity.quantityservice.repository;

import com.quantity.quantityservice.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByPerformedBy(String performedBy);
    List<QuantityMeasurementEntity> findByOperation(String operation);
}
