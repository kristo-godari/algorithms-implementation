package com.kristogodari.dfs;

import java.util.Stack;

public class Dfs {

    /**
     * Execute the DFS algorithm and print out each vertex value.
     * <p>
     * More information about dfs:
     * https://en.wikipedia.org/wiki/Depth-first_search
     *
     * @param rootVertex the starting vertex
     */
    public void executeDfsStartingWithVertex(Vertex rootVertex) {

        Stack<Vertex> stack = new Stack<>();

        rootVertex.setVisited(true);
        stack.add(rootVertex);

        while (!stack.isEmpty()) {

            Vertex currentVertex = stack.pop();
            System.out.println(currentVertex + " ");

            for (Vertex vertex : currentVertex.getNeighbors()) {

                if (!vertex.isVisited()) {
                    vertex.setVisited(true);
                    stack.push(vertex);
                }

            }
        }
    }
}