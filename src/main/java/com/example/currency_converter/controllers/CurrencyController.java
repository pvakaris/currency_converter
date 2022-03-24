package com.example.currency_converter.controllers;

import com.example.currency_converter.models.Currency;
import com.example.currency_converter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller of currencies.
 *
 * @author Vakaris Paulavicius
 * @version 1.5
 */
@RestController
@RequestMapping(path = "/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    /**
     * Constructor of currency controller.
     * @param currencyService Currency service object responsible for managing/modifying data.
     */
    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Used to return a JSON response containing a list of all the currencies currently stored in the database.
     * @return A List of Currency objects.
     */
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    /**
     * Used to get currency according to the specified name.
     * @param name Name of the currency.
     */
    @GetMapping(path = "/{name}")
    public Currency getCurrency(@PathVariable("name") String name) {
        return currencyService.getCurrency(name);
    }

    /**
     * Used to insert a new currency object into the database.
     * @param currency A new currency object which to insert to.
     */
    @PostMapping(path = "/insert")
    public void insertCurrency(@RequestBody Currency currency) {
        currencyService.insertCurrency(currency);
    }

    /**
     * Used to update the exchange rate of the currency "name".
     * @param name Name of the currency.
     * @param newCurrency New currency which to update to.
     */
    @PutMapping(path = "/update/{name}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable("name") String name, @RequestBody Currency newCurrency) {
        return currencyService.updateCurrency(name, newCurrency);
    }
}
