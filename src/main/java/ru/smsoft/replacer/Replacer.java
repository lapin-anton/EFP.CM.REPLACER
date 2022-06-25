package ru.smsoft.replacer;

import ru.smsoft.inserter.FileInserter;
import ru.smsoft.model.Param;
import ru.smsoft.parser.FileParser;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Replacer {

    public static void replace(Path cf, Path replacement) {
        List<Param> fromProject = FileParser.parse(cf);
        List<Param> fromReplacement = FileParser.parse(replacement);
        List<String> repNames = fromReplacement.stream().map(Param::getName)
                .collect(Collectors.toList());
        List<Param> filtered = fromProject.stream()
                .filter(p -> !repNames.contains(p.getName()))
                .collect(Collectors.toList());
        filtered.addAll(fromReplacement);
        FileInserter.insert(cf, filtered);
    }


}
