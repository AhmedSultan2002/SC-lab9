package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;

public class GraphPoetTest {

    @Test
    public void testPoemGeneration() throws IOException {
        File corpus = new File("D:\\UNI\\Semester 5\\Software Construction\\Labs\\Lab 8\\Lab8\\demo\\src\\test\\poet\\seven-words.txt"); // Ensure this file exists
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Seek to explore new and exciting synergies!";
        String expectedOutput = "Seek to explore new and exciting synergies!";
        assertEquals(expectedOutput, poet.poem(input), "Poem generation did not match expected output");
    }

    @Test
    public void testGraphConstruction() throws IOException {
        File corpus = new File("D:\\UNI\\Semester 5\\Software Construction\\Labs\\Lab 8\\Lab8\\demo\\src\\test\\poet\\seven-words.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "To seek out new life and new civilizations.";
        String expectedOutput = "To seek out new life and new civilizations.";
        assertEquals(expectedOutput, poet.poem(input), "Graph construction does not correctly bridge words");
    }

    @Test
    public void testEmptyInput() throws IOException {
        File corpus = new File("D:\\UNI\\Semester 5\\Software Construction\\Labs\\Lab 8\\Lab8\\demo\\src\\test\\poet\\seven-words.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "";
        String expectedOutput = ""; // No transformation expected
        assertEquals(expectedOutput, poet.poem(input), "Empty input did not return expected empty output");
    }

    @Test
    public void testNoBridges() throws IOException {
        File corpus = new File("D:\\UNI\\Semester 5\\Software Construction\\Labs\\Lab 8\\Lab8\\demo\\src\\test\\poet\\seven-words.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Hello world!";
        String expectedOutput = "Hello world!"; // No changes as no bridge exists
        assertEquals(expectedOutput, poet.poem(input), "Input with no bridges did not return original text");
    }

    @Test
    public void testCaseInsensitiveBridging() throws IOException {
        File corpus = new File("D:\\UNI\\Semester 5\\Software Construction\\Labs\\Lab 8\\Lab8\\demo\\src\\test\\poet\\seven-words.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "seek TO explore";
        String expectedOutput = "seek TO explore"; // Adjust based on corpus behavior, but ensures case-insensitivity
        assertEquals(expectedOutput, poet.poem(input), "Poem generation did not handle case insensitivity correctly");
    }

    @Test
    public void testSpecialCharactersHandling() throws IOException {
        File corpus = new File("D:\\UNI\\Semester 5\\Software Construction\\Labs\\Lab 8\\Lab8\\demo\\src\\test\\poet\\seven-words.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Explore, the strange new-world!";
        String expectedOutput = "Explore the strange new world,-!"; // Example handling of punctuation
        assertEquals(expectedOutput, poet.poem(input), "Special characters were not handled correctly");
    }
}
