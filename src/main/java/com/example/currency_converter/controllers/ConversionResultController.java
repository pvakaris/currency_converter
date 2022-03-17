package com.example.currency_converter.controllers;

import com.example.currency_converter.models.ConversionResult;
import com.example.currency_converter.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/convert")
public class ConversionResultController {

    private final ConversionService conversionService;

    @Autowired
    public ConversionResultController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public ConversionResult getConversion(@RequestParam String convertFrom, @RequestParam String convertTo, @RequestParam double amount) {
        return conversionService.getConversion(convertFrom, convertTo, amount);
    }
}
