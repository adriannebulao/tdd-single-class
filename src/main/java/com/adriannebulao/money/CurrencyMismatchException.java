package com.adriannebulao.money;

class CurrencyMismatchException extends RuntimeException {
    CurrencyMismatchException(String message) {
        super(message);
    }
}
