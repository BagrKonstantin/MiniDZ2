package org.example;

import org.json.JSONObject;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Ivan", "Ivanov", LocalDate.of(1997, 11, 2));
        JSONObject json = new JsonSerializer<>(Person.class).serialize(p);
        System.out.println(json);
    }
}