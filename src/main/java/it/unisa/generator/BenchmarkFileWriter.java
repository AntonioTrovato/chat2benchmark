package it.unisa.generator;

import java.io.*;
import java.nio.file.*;

public class BenchmarkFileWriter {
    public static void writeBenchmark(String originalJavaPath, String content) throws IOException {
        String microClassName = originalJavaPath.substring(originalJavaPath.lastIndexOf('/') + 1)
                .replace(".java", "Microbenchmark.java");

        String jmhPath = originalJavaPath
                .replace("/main/", "/jmh/")
                .replace(".java", "Microbenchmark.java");

        Path path = Paths.get(jmhPath);
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);
        System.out.println("Generated: " + path);
    }
}
