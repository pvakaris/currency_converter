package com.example.currency_converter.services;

import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public void insertCurrency(Currency newCurrency) {
        Optional<Currency> currency = currencyRepository.findById(newCurrency.getName());
        if(currency.isPresent()) {
            throw new IllegalStateException("Currency " + newCurrency.getName() + " already exists in the database.");
        }
        else {
            currencyRepository.save(newCurrency);
        }
    }
    
    public void updateCurrency(String name, double exchangeRate) {
        Currency currency = currencyRepository.findById(name).orElseThrow(() -> new IllegalStateException(
                "Currency " + name + "does not exist in the database."));
        currency.setExchangeRate(exchangeRate);
    }
}
