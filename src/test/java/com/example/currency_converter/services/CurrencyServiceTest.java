package com.example.currency_converter.services;

import com.example.currency_converter.exceptions.CustomException;
import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * This class test the behaviour of the CurrencyService class.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    private CurrencyService service;

    /**
     * Test suite setup.
     */
    @BeforeEach
    void setUp() {
        service = new CurrencyService(currencyRepository);
    }


    @Test
    void getAllCurrenciesGetsAllCurrenciesStoredInTheDatabase() {
        service.getAllCurrencies();
        verify(currencyRepository).findAll();
    }

    @Test
    void getCurrencyReturnsAnExistingCurrencyIfItExists() {
        String name = "EUR";
        Currency currency = new Currency("EUR", 1);

        Mockito.when(currencyRepository.findById(currency.getName()))
                .thenReturn(Optional.of(currency));

        assertThat(service.getCurrency(name)).isEqualTo(currency);
    }

    @Test
    void getCurrencyThrowsExceptionIfNameDoesNotExist() {
        String name = "EUR";
        assertThatThrownBy(() -> service.getCurrency(name))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency " + name + " does not exist in the database.");
    }

    @Test
    void canInsertNewCurrency() {
        Currency currency = new Currency("EUR", 1);
        service.insertCurrency(currency);

        ArgumentCaptor<Currency> currencyArgumentCaptor = ArgumentCaptor.forClass(Currency.class);
        verify(currencyRepository).save(currencyArgumentCaptor.capture());

        Currency capturedCurrency = currencyArgumentCaptor.getValue();
        assertThat(capturedCurrency).isEqualTo(currency);
    }

    @Test
    void cannotInsertNewCurrencyWithANameThatAlreadyExistsInTheDatabase() {
        Currency currency = new Currency("EUR", 1);
        given(currencyRepository.existsById(currency.getName())).willReturn(true);

        assertThatThrownBy(() -> service.insertCurrency(currency))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency " + currency.getName() + " already exists in the database, so it cannot be inserted again.");

        verify(currencyRepository, never()).save(currency);
    }

    @Test
    void updateCurrencyThrowsAnExceptionWhenThereIsNotCurrencyWithTheGivenName() {
        String name = "EUR";
        Currency currency = new Currency("EUR", 1);
        assertThatThrownBy(() -> service.updateCurrency(name, currency))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency " + name + " does not exist in the database, so it cannot be updated.");
    }

    @Test
    void currencyNameInTheURLMustBeTheSameAsTheCurrencyContainedInRequestBody() {
        Currency currencyEUR = new Currency("EUR", 1);
        Mockito.when(currencyRepository.findById(currencyEUR.getName()))
                .thenReturn(Optional.of(currencyEUR));


        String name = "EUR";
        Currency currency = new Currency("USD", 0.8);

        assertThatThrownBy(() -> service.updateCurrency(name, currency))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency name in the URL is not the same as the one in the request body. " + name + " != " + currency.getName() + ".");
    }

    @Test
    void exchangeRateForTheCurrencyWeWantToUpdateCannotBeSetToZero() {
        Currency currencyEUR = new Currency("EUR", 1);
        Mockito.when(currencyRepository.findById(currencyEUR.getName()))
                .thenReturn(Optional.of(currencyEUR));


        String name = "EUR";
        Currency currency = new Currency("EUR", 0);

        assertThatThrownBy(() -> service.updateCurrency(name, currency))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Currency exchange rate cannot be 0.");
    }

    @Test
    void updateCurrencySuccessfullyWithCorrectParameters() {
        Currency currencyEUR = new Currency("EUR", 1);
        Mockito.when(currencyRepository.findById(currencyEUR.getName()))
                .thenReturn(Optional.of(currencyEUR));
        Mockito.when(currencyRepository.getById(currencyEUR.getName()))
                .thenReturn(currencyEUR);


        String name = "EUR";
        Currency currency = new Currency("EUR", 3.45);

        service.updateCurrency(name, currency);
        Currency updated = currencyRepository.getById(name);
        assertThat(updated.getExchangeRate()).isEqualTo(currency.getExchangeRate());
    }
}