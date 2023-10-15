package com.zhdanov.T1consulting.Controllers;

import com.zhdanov.T1consulting.Services.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/getcountchars")
public class StringController {

    private final StringService service;

    @Autowired
    public StringController(StringService service) {
        this.service = service;
    }

    //По заданию необходимо продумать формат и ограничения входящих параметров.
    //Ограничение длины строки запроса - 50 символов
    //Цифры и спец.символы не учитываются
    //Заглавные и строчные буквы считаются как одна буква: запрос в формате "Аа" выведет ответ "а": 2
    @GetMapping
    public ResponseEntity<String> getCountCharsFromString (@RequestBody String str) {
        String resultString = service.getCountChars(str);
        return ResponseEntity.ok().body(resultString);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Пустой запрос!" + "\n" + "Введите корректный запрос.");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIncorrectRequestException (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}