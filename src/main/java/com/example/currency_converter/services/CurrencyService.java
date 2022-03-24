package com.example.currency_converter.services;

import com.example.currency_converter.exceptions.CustomException;
import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * A service that provides methods to edit, retrieve, insert currencies.
 *
 * @author Vakaris Paulavicius
 * @version 1.8
 */
@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    /**
     * Public CurrencyService constructor.
     * @param currencyRepository Repository where Currency objects are stored.
     */
    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    /**
     * Used to get a list of all the currencies that are currently being stored in the repository.
     * @return A list of all Currency objects.
     */
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }


    /**
     * Used to get the Currency object by name.
     * @param name Currency object name.
     * @return Currency object if one was found, throw an exception otherwise.
     */
    public Currency getCurrency(String name) {
        return currencyRepository.findById(name).orElseThrow(() -> new CustomException(
                "Currency " + name + " does not exist in the database."));
    }

    /**
     * Used to insert a new currency to the repository.
     * @param newCurrency New currency which to insert to.
     */
    public void insertCurrency(Currency newCurrency) {
        boolean exists = currencyRepository.existsById(newCurrency.getName());
        if(exists) {
            throw new CustomException("Currency " + newCurrency.getName() + " already exists in the database, so it cannot be inserted again.");
        }
        else {
            currencyRepository.save(newCurrency);
        }
    }

    /**
     * Used to update the exchange rate of the currency.
     * @param name Name of the currency.
     * @param newCurrency New currency which to update to.
     */
    public ResponseEntity<Currency> updateCurrency(String name, Currency newCurrency) {
        Currency currency = currencyRepository.findById(name).orElseThrow(() -> new CustomException(
                "Currency " + name + " does not exist in the database, so it cannot be updated."));
        if(null != newCurrency.getName() && newCurrency.getName().equals(currency.getName())) {
            if(newCurrency.getExchangeRate() == 0) {
                throw new CustomException("Currency exchange rate cannot be 0.");
            }
            else {
                currency.setExchangeRate(newCurrency.getExchangeRate());
                final Currency updatedCurrency = currencyRepository.save(currency);
                return ResponseEntity.ok(updatedCurrency);
            }
        }
        else {
            throw new CustomException("Currency name in the URL is not the same as the one in the request body. " + name + " != " + newCurrency.getName() + ".");
        }
    }
}
