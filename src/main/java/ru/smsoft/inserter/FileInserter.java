package ru.smsoft.inserter;

import ru.smsoft.model.Param;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class FileInserter {

    public static void insert(Path cf, List<Param> fromProject) {
        String head = takeHead(cf);
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(cf.toString()))) {
            StringBuilder replacementStr = new StringBuilder(head);
            fromProject.forEach(item -> replacementStr.append(String.format("  %s: %s%n", item.getName(), item.getValue())));
            pw.println(replacementStr);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static String takeHead(Path cf) {
        StringBuilder rsl = new StringBuilder("");
        try (BufferedReader br = new BufferedReader(new FileReader(cf.toString()))) {
            while (br.ready()) {
                String line = br.readLine();
                rsl.append(line);
                rsl.append("\n");
                if (line.trim().equals("data:")) {
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }

}
