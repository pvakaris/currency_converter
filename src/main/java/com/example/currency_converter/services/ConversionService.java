package com.example.currency_converter.services;

import com.example.currency_converter.models.ConversionResult;
import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConversionService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public ConversionService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public ConversionResult getConversion(String convertFrom, String convertTo, double amount) {

        Currency currencyFrom = currencyRepository.findById(convertFrom).orElseThrow(() -> new IllegalStateException(
                convertFrom + " currency does not exist in the database."));
        Currency currencyTo = currencyRepository.findById(convertTo).orElseThrow(() -> new IllegalStateException(
                convertTo + " currency does not exist in the database."));

        double result = convert(amount, currencyFrom.getExchangeRate(), currencyTo.getExchangeRate());
        return new ConversionResult(convertFrom, convertTo, amount, result);
    }

    public double convert(double amount, double exchangeRateFrom, double exchangeRateTo) {
        double value = amount * exchangeRateFrom / exchangeRateTo;
        // Use BigDecimal to round double to 18 places after the comma.
        BigDecimal rounder = new BigDecimal(Double.toString(value));
        rounder = rounder.setScale(18, RoundingMode.HALF_UP);

        return rounder.doubleValue();
    }
}
