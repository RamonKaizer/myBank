package br.com.myBank.myBank.exception;

public class ErrorBadRequestException extends RuntimeException{
        public ErrorBadRequestException(String message) {
            super(message);
        }
        public ErrorBadRequestException(String message, Throwable throwable) {
            super(message, throwable);
        }
}
