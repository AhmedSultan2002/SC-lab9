package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    @Override
    protected Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    @Test
    public void testInitialVerticesEmpty() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.vertices().isEmpty());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));
        assertFalse(graph.add("A"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        int prevWeight = graph.set("A", "B", 5);
        assertEquals(0, prevWeight);
        assertEquals(5, graph.set("A", "B", 3));
    }

    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals(5, graph.set("A", "B", 0));
    }

    @Test
    public void testVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        Set<String> vertices = graph.vertices();
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
        assertEquals(2, vertices.size());
    }
}
