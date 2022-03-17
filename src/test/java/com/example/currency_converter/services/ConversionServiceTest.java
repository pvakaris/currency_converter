package com.example.currency_converter.services;

import com.example.currency_converter.exceptions.CustomException;
import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This class tests the behaviour of the ConversionService.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class ConversionServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    private ConversionService service;

    /**
     * Test suite setup.
     */
    @BeforeEach
    void setUp() {
        service = new ConversionService(currencyRepository);
    }


    @Test
    void getConversionWillThrowExceptionIfConvertFromCurrencyDoesNotExist() {
        String convertFrom = "USD";
        String convertTo = "GBP";
        double amount = 100;
        assertThatThrownBy(() -> service.getConversion(convertFrom, convertTo, amount))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency " + convertFrom + " does not exist in the database.");
    }

    @Test
    void getConversionWillThrowExceptionIfConvertToCurrencyDoesNotExist() {
        Currency currencyFrom = new Currency("USD", 0.8);
        Mockito.when(currencyRepository.findById(currencyFrom.getName()))
                .thenReturn(Optional.of(currencyFrom));

        String convertFrom = "USD";
        String convertTo = "GBP";
        double amount = 100;
        assertThatThrownBy(() -> service.getConversion(convertFrom, convertTo, amount))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency " + convertTo + " does not exist in the database.");
    }

    @Test
    void conversionWithRightValuesGivesCorrectAnswer() {
        Currency currencyFrom = new Currency("USD", 0.8);
        Mockito.when(currencyRepository.findById(currencyFrom.getName()))
                .thenReturn(Optional.of(currencyFrom));

        Currency currencyTo = new Currency("GBP", 0.8);
        Mockito.when(currencyRepository.findById(currencyTo.getName()))
                .thenReturn(Optional.of(currencyTo));

        String convertFrom = "USD";
        String convertTo = "GBP";
        double amount = 100;

        assertThat(service.getConversion(convertFrom, convertTo, amount).getResultAmount()).isEqualTo(service.convert(amount, currencyFrom.getExchangeRate(), currencyTo.getExchangeRate()));
    }

    @Test
    void conversionTest() {
        double exchangeRateFrom = 0.8;
        double exchangeRateTo = 1.2;
        double amount = 100;

        assertThat(service.convert(amount, exchangeRateFrom, exchangeRateTo)).isEqualTo(amount * exchangeRateFrom / exchangeRateTo);
    }
}