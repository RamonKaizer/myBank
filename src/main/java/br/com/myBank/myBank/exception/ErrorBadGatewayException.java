package br.com.myBank.myBank.exception;

public class ErrorBadGatewayException extends RuntimeException{
    public ErrorBadGatewayException(String message) {
        super(message);
    }
}
