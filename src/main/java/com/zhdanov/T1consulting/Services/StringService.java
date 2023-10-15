package com.zhdanov.T1consulting.Services;

import com.zhdanov.T1consulting.Utils.IncorrectRequestException;
import com.zhdanov.T1consulting.Utils.StringLengthMoreThan50Exception;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StringService {

    public String getCountChars (String str) {

        Map<Character, Integer> map = new HashMap<>();
        StringBuilder s = new StringBuilder();

        //Проверка на тот случай, если длина строки превышает 50 символов
        if(str.length() > 50) {
            throw new StringLengthMoreThan50Exception();
        }

        for (Character ch : str.toLowerCase().toCharArray()) {

            if (Character.isLetter(ch)) {
                if (map.containsKey(ch)) {
                    map.put(ch, (map.get(ch) + 1));
                } else {
                    map.put(ch, 1);
                }
            }
        }

        //Проверка на тот случай, если запрос состоит из невалидных
        //символов (цифры, спец.символы, пробелы, переносы строк и т.д.)
        if (map.isEmpty()) {
            throw new IncorrectRequestException();
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        for (Map.Entry<Character, Integer> entry : list) {
            s.append(String.format("\"%s\": %d, ", entry.getKey(), entry.getValue()));
        }

        //Из результирующей строки удаляются последние 2 символа: запятая и пробел
        s.delete(s.length()-2, s.length());

     return s.toString();
    }
}
