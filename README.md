# This is a currency converter API.

_How to use (localhost | PORT 8080):_
___

**Get all the currencies**

    http://localhost:8080/currencies

___


**Get currency by name**

`{A} - name of the currency`

    http://localhost:8080/currencies/{A}

___


**Update currency by name**

`{A} - name of the currency which to update`
`{B} - new exchange rate which to change to`

    http://localhost:8080/currencies/update/{A}?exchangeRate={B}

___


**Insert a new currency to the database**

`Request body:`

    {

        "name" : currencyName

        "exchangeRate" : currencyExchangeRateToEUR

    }

 URL:

    http://localhost:8080/currencies/

___

G**et conversion result**

`{A} - name of the currency which to convert from` 

`{B} - name of the currency which to convert to` 

`{C} - the amount of currency A` 

    http://localhost:8080/convert?convertFrom={A}&convertTo={B}&amount={C}

___

Currently support currencies:
 - EUR
 - USD
 - GBP
 - BTC
 - ETH
 - FKE
___

Developed by _Vakaris Paulavicius_ for the company _Bankera_ as a recruitment task.

Version 1.0

2022-03-17


