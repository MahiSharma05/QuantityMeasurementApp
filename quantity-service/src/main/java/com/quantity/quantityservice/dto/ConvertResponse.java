package com.quantity.quantityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response body for POST /quantity/convert
 *
 * Example:
 * {
 *   "result": 212.0,
 *   "fromUnit": "CELSIUS",
 *   "toUnit": "FAHRENHEIT",
 *   "inputValue": 100.0
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertResponse {

    /** The converted value */
    private double result;

    /** Source unit name */
    private String fromUnit;

    /** Target unit name */
    private String toUnit;

    /** Original input value — useful for client-side display */
    private double inputValue;

    public static ConvertResponse of(double result, String from, String to, double input) {
        return new ConvertResponse(result, from, to, input);
    }
}