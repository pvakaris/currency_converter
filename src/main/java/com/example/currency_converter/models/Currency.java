package com.example.currency_converter.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Currency object which stores currency name and its exchange rate to EUR.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@Entity
@Table
public class Currency {

    @Id
    private String name;
    private double exchangeRate;

    /**
     * Currency constructor.
     * @param name Name of the currency.
     * @param exchangeRate Exchange rate of the currency to EUR.
     */
    public Currency(String name, double exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    /**
     * Empty Currency constructor.
     */
    public Currency() {

    }

    /**
     * Get currency name.
     * @return Name of the currency.
     */
    public String getName() {
        return name;
    }

    /**
     * Get currency rate.
     * @return Currency rate.
     */
    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Set the exchange rate of the currency.
     * @param exchangeRate New exchange rate which to set to.
     */
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
