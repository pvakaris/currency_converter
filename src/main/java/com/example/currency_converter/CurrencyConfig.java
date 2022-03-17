package com.example.currency_converter;

import com.example.currency_converter.models.Currency;
import com.example.currency_converter.repositories.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class configures initial Currency database.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@Configuration
public class CurrencyConfig {

    private static final String COMMA_DELIMITER = ",";

    /**
     * Get a list of currencies and save them in the Currency Repository.
     * @param repository Currency Repository which to save to.
     * @return CommandLineRunner object.
     */
    @Bean
    CommandLineRunner commandLineRunner(CurrencyRepository repository) {
        return args -> {
            List<Currency> currencies = getCurrencies();
            repository.saveAll(currencies);
        };
    }

    /**
     * Read the "data.csv" file containing currency names and exchange rate values (compared to euro),
     * and create a list of Currency objects.
     * @return List of Currency objects.
     */
    private List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.csv"))) {
            reader.readLine(); // Reads the first line which gives the description of values inside the csv.
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into name, exchange values.
                String[] values = line.split(COMMA_DELIMITER);
                // Create Currency objects accordingly.
                currencies.add(new Currency(values[0], Double.parseDouble(values[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
