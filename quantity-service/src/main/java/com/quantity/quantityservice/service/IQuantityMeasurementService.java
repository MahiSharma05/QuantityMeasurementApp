package com.quantity.quantityservice.service;

import com.quantity.quantityservice.dto.ConvertRequest;
import com.quantity.quantityservice.dto.ConvertResponse;
import com.quantity.quantityservice.dto.OperationHistoryResponse;
import com.quantity.quantityservice.dto.QuantityDTO;

import java.util.List;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO a, QuantityDTO b, String user);
    QuantityDTO convert(QuantityDTO a, QuantityDTO b, String user);
    QuantityDTO add(QuantityDTO a, QuantityDTO b, String user);
    QuantityDTO subtract(QuantityDTO a, QuantityDTO b, String user);
    double divide(QuantityDTO a, QuantityDTO b, String user);
    ConvertResponse convertUnits(ConvertRequest request, String user);

    // ONLY USER HISTORY
    List<OperationHistoryResponse> getUserHistory(String username);
}