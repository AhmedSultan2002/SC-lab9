package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ConcreteVerticesGraph implements Graph<String> {
    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction Function: Represents a graph where vertices are stored in a list and edges are represented by connections between vertices
    // Representation Invariant: vertices cannot be null, and no vertex can have duplicate edges

    @Override
    public boolean add(String vertex) {
        if (vertex == null) return false;
        for (Vertex v : vertices) {
            if (v.getName().equals(vertex)) return false; // Prevent duplicates
        }
        vertices.add(new Vertex(vertex));
        return true;
    }

    @Override
    public boolean remove(String vertex) {
        if (vertex == null) return false;
        Vertex toRemove = null;
        for (Vertex v : vertices) {
            if (v.getName().equals(vertex)) {
                toRemove = v;
                break;
            }
        }
        if (toRemove == null) return false;
        vertices.remove(toRemove);
        return true;
    }

    @Override
    public int set(String from, String to, int weight) {
        if (from == null || to == null) return -1;
        Vertex fromVertex = null;
        Vertex toVertex = null;

        // Find the vertices
        for (Vertex v : vertices) {
            if (v.getName().equals(from)) fromVertex = v;
            if (v.getName().equals(to)) toVertex = v;
        }
        if (fromVertex == null || toVertex == null) return -1; // One or both vertices not found

        // Add the edge
        return fromVertex.addEdge(toVertex, weight);
    }

    @Override
    public Set<String> vertices() {
        Set<String> vertexNames = new HashSet<>();
        for (Vertex vertex : vertices) {
            vertexNames.add(vertex.getName());
        }
        return vertexNames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        for (Vertex vertex : vertices) {
            sb.append(vertex).append("\n");
        }
        return sb.toString();
    }

    public void checkRep() {
        assert vertices != null : "Vertices list cannot be null";
        for (Vertex vertex : vertices) {
            assert vertex != null : "Vertex cannot be null";
        }
    }

    private static class Vertex {
        private final String name;
        private final List<Edge> edges = new ArrayList<>();

        Vertex(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int addEdge(Vertex to, int weight) {
            for (Edge e : edges) {
                if (e.to.equals(to)) {
                    int prevWeight = e.weight;
                    e.weight = weight;
                    return prevWeight;
                }
            }
            edges.add(new Edge(this, to, weight));
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(name + ": ");
            for (Edge e : edges) {
                sb.append(e).append(" ");
            }
            return sb.toString();
        }

        private static class Edge {
            private final Vertex from;
            private final Vertex to;
            private int weight;

            Edge(Vertex from, Vertex to, int weight) {
                this.from = from;
                this.to = to;
                this.weight = weight;
            }

            @Override
            public String toString() {
                return from.getName() + " -> " + to.getName() + " (Weight: " + weight + ")";
            }
        }
    }
}
