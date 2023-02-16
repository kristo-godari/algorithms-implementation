package com.kristogodari.topologicalorder;

import java.util.Stack;

public class TopologicalOrdering {

    private Stack<Vertex> stack;

    /**
     * Clas constructor
     */
    public TopologicalOrdering() {
        this.stack = new Stack<>();
    }

    /**
     * Depth first search implemented with the help
     * of recursion.
     *
     * @param vertex
     */
    public void depthFirstSearch(Vertex vertex) {

        vertex.setVisited(true);

        for (Vertex v : vertex.getNeighbors()) {
            if (!v.isVisited()) {
                depthFirstSearch(v);
            }
        }

        stack.push(vertex);
    }

    public Stack<Vertex> getStack() {
        return this.stack;
    }
}
