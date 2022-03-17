package com.example.currency_converter.models;

public class ConversionResult {

    final String convertedFrom;
    final String convertedTo;
    final double givenAmount;
    final double resultAmount;

    public ConversionResult(String convertedFrom, String convertedTo, double givenAmount, double resultAmount) {
        this.convertedFrom = convertedFrom;
        this.convertedTo = convertedTo;
        this.givenAmount = givenAmount;
        this.resultAmount = resultAmount;
    }

    public String getConvertedFrom() {
        return convertedFrom;
    }

    public String getConvertedTo() {
        return convertedTo;
    }

    public double getGivenAmount() {
        return givenAmount;
    }

    public double getResultAmount() {
        return resultAmount;
    }

}
