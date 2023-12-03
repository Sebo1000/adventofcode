package com.seb.input;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputReader {

    @SneakyThrows
    public String read(String fileName) {
        try (InputStream input = getClass().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Input file " + fileName + " not found");
            }
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        }
    }
}
