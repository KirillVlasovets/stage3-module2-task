package com.mjc.school.controller.utils;

import com.mjc.school.service.enums.Errors;
import com.mjc.school.service.exception.IncorrectDataException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScanUtils {
    private final Scanner scanner = new Scanner(System.in);

    public Long getNextLong() {
        try {
            return scanner.nextLong();
        } catch (Exception exception) {
            String incorrectLine = scanner.nextLine();
            throw new IncorrectDataException(String.format(Errors.INCORRECT_DATA_FORMAT.getMessage(), incorrectLine));
        }
    }
}
