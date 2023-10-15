package com.zhdanov.T1consulting;

import com.zhdanov.T1consulting.Services.StringService;
import com.zhdanov.T1consulting.Utils.IncorrectRequestException;
import com.zhdanov.T1consulting.Utils.StringLengthMoreThan50Exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringServiceTest {

    private final StringService stringService;

    @Autowired
    public StringServiceTest(StringService stringService) {
        this.stringService = stringService;
    }

    @Test
    public void getCountCharsTest () {
        String testRequest = "AaBbbCcccDdddd";
        String testResponse = "\"d\": 5, \"c\": 4, \"b\": 3, \"a\": 2";
        Assertions.assertEquals(testResponse, stringService.getCountChars(testRequest));
    }

    //Тест на случай, если строка содержит как буквы, так и цифры и спец.символы
    @Test
    public void getCountCharsForStringConsistOfLettersAndDigitsAndSpecialSymbols () {
        String testRequest = "1A2a!B@b#b$C5c c^c7D*d=d+d-d_";
        String testResponse = "\"d\": 5, \"c\": 4, \"b\": 3, \"a\": 2";
        Assertions.assertEquals(testResponse, stringService.getCountChars(testRequest));
    }

    //Тест на случай, если длина строки превышает 50 символов
    @Test
    public void getCountCharsForStringLengthMoreThan50Test () {
        RuntimeException exception = Assertions.assertThrows(StringLengthMoreThan50Exception.class, () -> {
            String testRequest = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
            stringService.getCountChars(testRequest);
        });
    }

    //Тест на случай, если строка состоит только из спец.символов
    @Test
    public void getCountCharsForStringConsistOfSpecialSymbols () {
        RuntimeException exception = Assertions.assertThrows(IncorrectRequestException.class, () -> {
            String testRequest = "!@#$%^&*()_+ ";
            stringService.getCountChars(testRequest);
        });
    }
}
