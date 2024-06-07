# tdd-single-class
Test-driven development on a single class exercise

## Requirements:
- Money class
- Each instance of Money stores a value which represents an amount of money
- The value contains currency and a decimal value precise to two (2) decimal places
- Currency is either PHP, USD, EUR
- Support for negative values of money
- toString() method should return the following formats:
  - PHP 1.50 / PHP -1.50
  - PHP 1.05 / PHP -1.05
  - PHP 0.05 / PHP -0.05
- Supports addition & subtraction with other Money object
  - Throws exception if currencies not the same
- Appropriate equals & hashcode methods

## Additional constraints:
- The numerical value of money should be stored as two integers, where one integer represents the “dollar” value and the other integer represents the “cents” value
- You may not use BigDecimal anywhere in your code
- You may not combine the two int fields into a single number to ease computations
- Do not use String.format(...) to pad zeroes