package com.example;

import org.junit.jupiter.api.Test;

public abstract class GraphInstanceTest {
    
    // This method will be provided by subclasses to return new empty Graph instances
    protected abstract Graph<String> emptyInstance();

    @Test
    public void testInitialVerticesEmpty() {
    }

    @Test
    public void testAddVertex() {
    }

    @Test
    public void testRemoveVertex() {
    }

    @Test
    public void testAddEdge() {
    }

    @Test
    public void testRemoveEdge() {
    }

    @Test
    public void testVertices() {
    }
    
}
