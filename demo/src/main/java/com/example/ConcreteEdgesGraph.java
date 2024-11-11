package com.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ConcreteEdgesGraph implements Graph<String> {
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction Function: Represents a directed graph where vertices are stored in a set and edges in a list
    // Representation Invariant: vertices cannot be null, edges cannot be null, and no duplicate edges between same vertices

    @Override
    public boolean add(String vertex) {
        if (vertex == null) return false;
        return vertices.add(vertex);
    }

    @Override
    public boolean remove(String vertex) {
        if (vertex == null || !vertices.contains(vertex)) return false;
        // Remove all edges involving this vertex
        edges.removeIf(edge -> edge.from.equals(vertex) || edge.to.equals(vertex));
        return vertices.remove(vertex);
    }

    @Override
    public int set(String from, String to, int weight) {
        if (from == null || to == null) return -1;
        Edge newEdge = new Edge(from, to, weight);
        for (Edge edge : edges) {
            if (edge.from.equals(from) && edge.to.equals(to)) {
                int prevWeight = edge.weight;
                edge.weight = weight;
                return prevWeight;
            }
        }
        edges.add(newEdge);
        return 0;
    }

    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        for (Edge edge : edges) {
            sb.append(edge).append("\n");
        }
        return sb.toString();
    }

    public void checkRep() {
        assert vertices != null : "Vertices set cannot be null";
        assert edges != null : "Edges list cannot be null";
        for (Edge edge : edges) {
            assert edge != null : "Edge cannot be null";
            assert vertices.contains(edge.from) : "Edge from vertex not in the graph";
            assert vertices.contains(edge.to) : "Edge to vertex not in the graph";
        }
    }

    private static class Edge {
        private final String from;
        private final String to;
        private int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " -> " + to + " (Weight: " + weight + ")";
        }
    }
}
