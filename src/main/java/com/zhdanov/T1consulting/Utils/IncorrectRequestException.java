package com.zhdanov.T1consulting.Utils;

public class IncorrectRequestException extends RuntimeException{

    String message = "Некорректный запрос!" + "\n" + "Введите корректный запрос.";

    public IncorrectRequestException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
