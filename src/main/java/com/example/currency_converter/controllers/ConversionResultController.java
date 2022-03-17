package com.example.currency_converter.controllers;

import com.example.currency_converter.models.ConversionResult;
import com.example.currency_converter.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller of the conversion.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/convert")
public class ConversionResultController {

    private final ConversionService conversionService;

    /**
     * Constructor of currency result controller.
     * @param conversionService Currency conversion service object responsible for converting from one currency to another.
     */
    @Autowired
    public ConversionResultController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * Used to get the conversion result object.
     * @param convertFrom Name of the currency which to convert from.
     * @param convertTo Name of the currency which to convert to.
     * @param amount Amount of the "convertFrom" currency units.
     * @return ConversionResult object containing the result of the conversion and all the other relevant data.
     */
    @GetMapping
    public ConversionResult getConversion(@RequestParam String convertFrom, @RequestParam String convertTo, @RequestParam double amount) {
        return conversionService.getConversion(convertFrom, convertTo, amount);
    }
}
