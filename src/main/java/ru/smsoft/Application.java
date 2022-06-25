package ru.smsoft;

import ru.smsoft.parser.ArgsName;
import ru.smsoft.replacer.Replacer;
import ru.smsoft.searcher.Search;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Application {

    private static final String PATH_FORMAT = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateArg(argsName.get("f"));
        validateArg(argsName.get("cm"));
        Path start = Paths.get(argsName.get("f"));
        Path replacement = Paths.get(argsName.get("cm"));
        List<Path> configFiles = Search.search(start, p -> p.toFile().getName().endsWith("-cm.yml"));
        configFiles.forEach(cf -> Replacer.replace(cf, replacement));
    }

    private static void validateArg(String argument) {
        if (!Pattern.matches(PATH_FORMAT, argument)) {
            throw new IllegalArgumentException(String.format("Argument '%s' has incorrect path format", argument));
        }
    }

}
