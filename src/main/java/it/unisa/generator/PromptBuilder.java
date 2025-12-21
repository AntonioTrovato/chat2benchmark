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
        sb.append("\nIMPORTANT:\n");
        sb.append("- Output ONLY valid Java code\n");
        sb.append("- Do NOT include explanations, notes, comments, or markdown\n");
        sb.append("- Do NOT include ``` or any text outside the Java class\n");
        sb.append("- The output must be directly compilable\n");
        return sb.toString();
    }
}
