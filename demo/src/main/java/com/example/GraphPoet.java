package com.example;

import java.io.*;
import java.util.*;

/**
 * GraphPoet generates poetic output based on a corpus of text.
 * It constructs a word affinity graph using in-order adjacency counts.
 */
public class GraphPoet {
    private final Graph<String> graph = new ConcreteEdgesGraph<>();

    /**
     * Constructs a GraphPoet using the given corpus file.
     * 
     * @param corpus the corpus file to build the word affinity graph
     * @throws IOException if an error occurs while reading the file
     */
    public GraphPoet(File corpus) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(corpus))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\W+"); // split by non-word characters
                for (int i = 0; i < words.length - 1; i++) {
                    String source = words[i];
                    String target = words[i + 1];
                    
                    // Increment the weight of the edge between source and target
                    int currentWeight = graph.set(source, target, 0);
                    graph.set(source, target, currentWeight + 1);
                }
            }
        }
    }

    /**
     * Generates a poem by transforming input using the word affinity graph.
     * 
     * @param input the input string
     * @return the transformed poetic output
     */
    public String poem(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\W+"); // split by non-word characters

        if (words.length == 0) {
            return ""; // Return empty for no input
        }

        result.append(words[0]);

        // Capture and preserve punctuation
        String punctuation = input.replaceAll("[a-zA-Z0-9 ]", "");

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i].toLowerCase();
            String word2 = words[i + 1].toLowerCase();
            String bridge = findBridge(word1, word2);

            if (bridge != null) {
                result.append(" ").append(bridge);
            }
            result.append(" ").append(words[i + 1]);
        }

        // Add punctuation back to the result
        result.append(punctuation);

        return result.toString();
    }

    /**
     * Finds a bridge word between two words if it exists.
     * 
     * @param word1 the source word
     * @param word2 the target word
     * @return the bridge word or null if no bridge is found
     */
    private String findBridge(String word1, String word2) {
        Set<String> neighbors = graph.vertices();
        String bridge = null;
        int maxWeight = 0;

        for (String potentialBridge : neighbors) {
            int weight1 = graph.set(word1, potentialBridge, 0);
            int weight2 = graph.set(potentialBridge, word2, 0);
            if (weight1 > 0 && weight2 > 0) {
                int combinedWeight = weight1 + weight2;
                if (combinedWeight > maxWeight) {
                    maxWeight = combinedWeight;
                    bridge = potentialBridge;
                }
            }
        }
        return bridge;
    }

    @Override
    public String toString() {
        return "GraphPoet with graph: " + graph.toString();
    }
}
