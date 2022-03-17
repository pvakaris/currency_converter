package com.example.currency_converter.services;

import com.example.currency_converter.models.ConversionResult;
import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class converts from one currency to another.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@Service
public class ConversionService {

    private final CurrencyRepository currencyRepository;

    /**
     * Public constructor of the Conversion Service.
     * @param currencyRepository Repository which stores all the currencies and their exchange rates.
     */
    @Autowired
    public ConversionService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    /**
     * Used to get the ConversionResult object holding the result of the conversion and all the other relevant data.
     * @param convertFrom Name of the currency which to convert from.
     * @param convertTo Name of the currency which to convert to.
     * @param amount Amount of the currency which to convert from.
     * @return ConversionResult.
     */
    public ConversionResult getConversion(String convertFrom, String convertTo, double amount) {
        Currency currencyFrom = currencyRepository.findById(convertFrom).orElseThrow(() -> new IllegalStateException(
                convertFrom + " currency does not exist in the database."));
        Currency currencyTo = currencyRepository.findById(convertTo).orElseThrow(() -> new IllegalStateException(
                convertTo + " currency does not exist in the database."));
        double result = convert(amount, currencyFrom.getExchangeRate(), currencyTo.getExchangeRate());
        return new ConversionResult(convertFrom, convertTo, amount, result);
    }

    /**
     * Used to convert the amount from one currency to another using their exchange rates.
     * Requested currency is converted to EUR and then from EUR to the required currency.
     * @param amount Amount which to convert.
     * @param exchangeRateFrom Exchange rate of the current currency.
     * @param exchangeRateTo Exchange rate of the required currency.
     * @return A converted value with up to 18 digits after the comma.
     */
    public double convert(double amount, double exchangeRateFrom, double exchangeRateTo) {
        double value = amount * exchangeRateFrom / exchangeRateTo;
        // Use BigDecimal to round double to 18 places after the comma.
        BigDecimal rounder = new BigDecimal(Double.toString(value));
        rounder = rounder.setScale(18, RoundingMode.HALF_UP);

        return rounder.doubleValue();
    }
}
