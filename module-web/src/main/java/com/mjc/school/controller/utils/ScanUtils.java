package com.mjc.school.controller.utils;

import com.mjc.school.service.enums.Errors;
import com.mjc.school.service.exception.IncorrectDataException;

import java.util.Scanner;

public class ScanUtils {

    public static Long getNextLong(Scanner scanner) {
        try {
            return scanner.nextLong();
        } catch (Exception exception) {
            String incorrectLine = scanner.nextLine();
            throw new IncorrectDataException(String.format(Errors.INCORRECT_DATA_FORMAT.getMessage(), incorrectLine));
        }
    }
}
