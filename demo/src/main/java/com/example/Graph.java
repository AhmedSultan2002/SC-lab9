package com.example;

import java.util.Set;

public interface Graph<T> {
    
    /**
     * Add a vertex to the graph.
     * 
     * @param vertex the vertex to add
     * @return true if the vertex was added, false if it was already in the graph
     */
    boolean add(T vertex);

    /**
     * Remove a vertex from the graph.
     * 
     * @param vertex the vertex to remove
     * @return true if the vertex was removed, false if it was not in the graph
     */
    boolean remove(T vertex);

    /**
     * Add, update, or remove a weighted directed edge from source to target.
     * 
     * @param source the source vertex
     * @param target the target vertex
     * @param weight if zero, removes the edge; otherwise, sets the edge's weight to this value
     * @return the previous weight of the edge, or zero if there was no previous edge
     */
    int set(T source, T target, int weight);

    /**
     * Get all vertices in the graph.
     * 
     * @return a set containing all vertices in the graph
     */
    Set<T> vertices();
}
