package ru.smsoft.parser;

import ru.smsoft.model.Param;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileParser {

    private static final String PARAM_PATTERN = "[\\w]+: ['\\\"][\\w-./:% ]*['\\\"]";

    public static List<Param> parse(Path cf) {
        List<Param> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cf.toString()))) {
            while (br.ready()) {
                String line = br.readLine().trim();
                if (Pattern.matches(PARAM_PATTERN, line)) {
                    rsl.add(getParamPair(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private static Param getParamPair(String line) {
        String name = line.substring(0, line.indexOf(':')).trim();
        String value = line.substring(name.length() + 1).trim();
        return new Param(name, value);
    }
}
