## Currency converter API 

_How to use (localhost | PORT 8080):_
___

**Get all the currencies:**

    http://localhost:8080/currencies

___


**Get currency by name:**

`{A} - name of the currency`

    http://localhost:8080/currencies/{A}

___


**Update currency by name:**

`Request body:`

    {

        "name" : currencyName

        "exchangeRate" : newCurrencyExchangeRateToEUR

    }

`URL:`
`{A} - name of the currency which to update`

    http://localhost:8080/currencies/update/{A}

___


**Insert a new currency into the database:**

`Request body:`

    {

        "name" : currencyName

        "exchangeRate" : currencyExchangeRateToEUR

    }

`URL:`

    http://localhost:8080/currencies/insert

___

**Get conversion result:**

`{A} - name of the currency which to convert from` 

`{B} - name of the currency which to convert to` 

`{C} - the amount of currency A` 

    http://localhost:8080/convert?convertFrom={A}&convertTo={B}&amount={C}

___

Supported currencies:
 - EUR
 - USD
 - GBP
 - BTC
 - ETH
 - FKE
___

Developed by _Vakaris Paulavičius_ for _Bankera_ as a recruitment task.

Version 1.1.2

2022-03-24


