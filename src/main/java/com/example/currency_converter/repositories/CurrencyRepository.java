package com.example.currency_converter.repositories;

import com.example.currency_converter.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for storing Currency objects locally.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> { }