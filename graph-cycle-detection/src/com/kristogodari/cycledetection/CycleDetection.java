package com.kristogodari.cycledetection;

import java.util.List;

public class CycleDetection {

    public void detect(List<Vertex> vertexArrayList) {

        for (Vertex v : vertexArrayList) {
            if (!v.isVisited()) {
                depthFirstSearch(v);
            }
        }
    }

    private void depthFirstSearch(Vertex vertex) {

        vertex.setBeingVisited(true);

        for (Vertex v : vertex.getNeighbors()) {

            if (v.isBeingVisited()) {
                System.out.println("Cycle detected!");
                return;
            }

            if (!v.isVisited()) {
                v.setVisited(true);
                depthFirstSearch(v);
            }
        }

        vertex.setBeingVisited(false);
        vertex.setVisited(true);
    }
}
