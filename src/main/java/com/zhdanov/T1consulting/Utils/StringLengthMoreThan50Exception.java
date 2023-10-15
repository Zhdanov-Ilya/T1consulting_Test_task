package com.zhdanov.T1consulting.Utils;

public class StringLengthMoreThan50Exception extends RuntimeException{

    String message = "Длина строки превышает 50 символов!" + "\n" + "Введите корректный запрос.";

    public StringLengthMoreThan50Exception() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
