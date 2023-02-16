package com.kristogodari.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

    /**
     * Execute the BFS algorithm and print out each vertex value.
     *
     * More information about bfs:
     * https://en.wikipedia.org/wiki/Breadth-first_search
     *
     * @param rootVertex the starting vertex
     */
    public void executeBfsStartingWithVertex(Vertex rootVertex) {

        Queue<Vertex> queue = new LinkedList<>();

        rootVertex.setVisited(true);
        queue.add(rootVertex);

        while (!queue.isEmpty()) {

            Vertex currentVertex = queue.remove();
            System.out.println(currentVertex + " ");

            for (Vertex vertex : currentVertex.getNeighbors()) {

                if (!vertex.isVisited()) {
                    vertex.setVisited(true);
                    queue.add(vertex);
                }

            }
        }
    }
}
