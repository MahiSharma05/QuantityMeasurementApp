package com.quantity.quantityservice.repository;

import com.quantity.quantityservice.entity.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {

    List<OperationHistory> findByUsernameOrderByTimestampDesc(String username);

}