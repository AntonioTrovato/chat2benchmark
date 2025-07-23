package it.unisa.generator;

import java.util.List;

public class PromptBuilder {
    public static String buildBenchmarkPrompt(String filePath, List<String> methods) {
        String className = filePath.substring(filePath.lastIndexOf('/') + 1).replace(".java", "");
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a JMH benchmark class called ").append(className).append("Microbenchmark ");
        sb.append("for the Java class ").append(className).append(".\n");
        sb.append("Include @Benchmark methods to measure performance of: ");
        sb.append(String.join(", ", methods)).append(".\n");
        sb.append("Use @State(Scope.Thread), @Setup(Level.Invocation), and JMH best practices.\n");
        sb.append("Assume necessary imports and helper setup code.\n");
        return sb.toString();
    }
}
