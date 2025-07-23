package it.unisa.generator;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 3 || !args[1].equals("-host")) {
            System.err.println("Usage: java -jar chat2benchmark.jar <input.json> -host <llm_url> [-model <model>] [-tmp <temperature>]");
            System.exit(1);
        }

        String jsonPath = args[0];
        String hostUrl = args[2];

        String model = "codellama-13b-instruct";
        double temperature = 0.3;

        for (int i = 3; i < args.length; i++) {
            switch (args[i]) {
                case "-llm":
                    model = args[++i];
                    break;
                case "-tmp":
                    temperature = Double.parseDouble(args[++i]);
                    break;
            }
        }

        LLMClient.configure(hostUrl, model, temperature);

        Map<String, List<String>> input = JsonInputParser.parse(jsonPath);

        for (String javaPath : input.keySet()) {
            List<String> methods = input.get(javaPath);
            String prompt = PromptBuilder.buildBenchmarkPrompt(javaPath, methods);
            String code = LLMClient.generate(prompt);
            BenchmarkFileWriter.writeBenchmark(javaPath, code);
        }

        System.out.println("Benchmarks generated.");
    }
}
