package com.mjc.school.repository.domain;

import lombok.NoArgsConstructor;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@NoArgsConstructor
public class Utils {

    public List<String> readFile(String path) {
        List<String> filesStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                filesStrings.add(line);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return filesStrings;
    }

    public void addLineInFile(String path, String newLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(newLine);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getTime() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}
