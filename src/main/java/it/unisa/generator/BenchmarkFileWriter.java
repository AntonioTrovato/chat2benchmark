package it.unisa.generator;

import java.io.*;
import java.nio.file.*;

public class BenchmarkFileWriter {
    public static void writeBenchmark(String originalJavaPath, String content) throws IOException {
        String jmhPath = originalJavaPath
                .replace("/main/", "/jmh/")
                .replace(".java", "Benchmark.java");

        Path path = Paths.get(jmhPath);
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);
        System.out.println("Generated: " + path);
    }

    public static void writeFixedBenchmark(String benchmarkPath, String content) throws IOException {
        Path path = Paths.get(benchmarkPath);
        Files.writeString(path, content);
        System.out.println("Fixed: " + path);
    }
}
