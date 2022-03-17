package com.example.currency_converter.controllers;

import com.example.currency_converter.models.Currency;
import com.example.currency_converter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @PostMapping
    public void postCurrency(@RequestBody Currency currency) {
        currencyService.insertCurrency(currency);
    }

    @PutMapping(path = "{name}")
    public void updateCurrency(@PathVariable("name") String name, @RequestParam(required = false) double exchangeRate) {
        currencyService.updateCurrency(name, exchangeRate);
    }
}
