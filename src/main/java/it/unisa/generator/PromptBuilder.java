package it.unisa.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PromptBuilder {
    public static String buildBenchmarkPrompt(String filePath, List<String> methods) throws IOException {
        String className = filePath.substring(filePath.lastIndexOf('/') + 1).replace(".java", "");
        String code = Files.readString(Paths.get(filePath));

        StringBuilder sb = new StringBuilder();
        sb.append("Generate a JMH 1.37 benchmark class called ").append(className).append("Benchmark ");
        sb.append("for the Java class ").append(className).append(":\n\n");
        sb.append(code).append("\n\n");
        sb.append("Include @Benchmark methods to measure performance ONLY of methods: ");
        sb.append(String.join(", ", methods)).append(".\n");
        return sb.toString();
    }
}
