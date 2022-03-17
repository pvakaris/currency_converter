package com.example.currency_converter.models;

/**
 * This class represents a result of the conversion from one currency to another.
 * It's used to return a JSON response.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
public class ConversionResult {

    final String convertedFrom;
    final String convertedTo;
    final double givenAmount;
    final double resultAmount;

    /**
     * Constructor of the ConversionResult.
     * @param convertedFrom Name of the currency which the amount was converted from.
     * @param convertedTo Name of the currency which the amount was converted to.
     * @param givenAmount Initial amount of "convertedFrom" currency.
     * @param resultAmount Result amount of the conversion. How many units of "convertedTo" are available after the conversion.
     */
    public ConversionResult(String convertedFrom, String convertedTo, double givenAmount, double resultAmount) {
        this.convertedFrom = convertedFrom;
        this.convertedTo = convertedTo;
        this.givenAmount = givenAmount;
        this.resultAmount = resultAmount;
    }

}
