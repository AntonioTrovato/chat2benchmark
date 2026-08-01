package it.unisa.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PromptBuilder {
    public static String buildBenchmarkPrompt(String filePath, List<String> methods) throws IOException {
        String className = filePath.substring(filePath.lastIndexOf('/') + 1).replace(".java", "");
        String code = Files.readString(Paths.get(filePath));

        return "Generate a JMH 1.37 benchmark class called " + className + "Benchmark " +
                "for the Java class " + className + ":\n\n" +
                code + "\n\n" +
                "Include @Benchmark methods to measure performance ONLY of methods: " +
                String.join(", ", methods) + ".\n" +
                "\nIMPORTANT:\n" +
                "- Output ONLY valid Java code\n" +
                "- Do NOT include explanations, notes, comments, or markdown\n" +
                "- Do NOT include ``` or any text outside the Java class\n" +
                "- The output must be directly compilable\n";
    }

    public static String buildFixPrompt(String code, String errorMessage) {
        return "Correct the following JMH benchmark class:\n\n" +
                code + "\n\n" +
                "This is the error message I got:\n\n" +
                errorMessage + "\n\n" +
                "Output instructions:\n" +
                "  - Rewrite the correct JMH 1.37 Benchmark class\n" +
                "  - Output ONLY valid Java code\n" +
                "  - Do NOT include explanations, notes, comments, or markdown\n" +
                "  - Do NOT include ``` or any text outside the Java class\n" +
                "  - The output must be directly compilable\n";
    }
}
