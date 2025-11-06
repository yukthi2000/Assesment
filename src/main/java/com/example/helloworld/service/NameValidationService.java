package com.example.helloworld.service;

import org.springframework.stereotype.Service;


@Service
public class NameValidationService {


    public boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        char firstLetter = Character.toLowerCase(name.trim().charAt(0));
        return firstLetter >= 'a' && firstLetter <= 'm';
    }


    public String formatName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        String trimmedName = name.trim();
        return trimmedName.substring(0, 1).toUpperCase() + 
               trimmedName.substring(1).toLowerCase();
    }
}
