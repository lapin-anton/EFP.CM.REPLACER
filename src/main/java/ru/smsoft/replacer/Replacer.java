package ru.smsoft.replacer;

import ru.smsoft.inserter.FileInserter;
import ru.smsoft.model.Param;
import ru.smsoft.parser.FileParser;

import java.nio.file.Path;
import java.util.List;

public class Replacer {

    public static void replace(Path cf, Path replacement) {
        List<Param> fromProject = FileParser.parse(cf);
        List<Param> fromReplacement = FileParser.parse(replacement);

        for (Param rep: fromReplacement) {
            for (Param prj: fromProject) {
                if (rep.getName().equals(prj.getName())) {
                    prj.setValue(rep.getValue());
                }
            }
        }

        FileInserter.insert(cf, fromProject);
    }


}
