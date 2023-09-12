package br.com.myBank.myBank.exception;

public class ErroException extends RuntimeException{
        public ErroException(String message) {
            super(message);
        }
        public ErroException(String message, Throwable throwable) {
            super(message, throwable);
        }

}
