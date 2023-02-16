package com.kristogodari.dfs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    /**
     * Node/Vertex data, this can be anything, in my case is String
     * because my graph is letter based.
     */
    private String data;

    /**
     * If this vertex is visited.
     */
    private boolean visited;

    /**
     * List with this vertex neighbpors.
     */
    private List<Vertex> neighbors;

    /**
     * Class constructor
     *
     * @param data
     */
    public Vertex(String data) {
        this.data = data;
        neighbors = new ArrayList<>();
    }

    /**
     * Adds neigbhor
     *
     * @param neigbhor
     */
    public void addNeighbor(Vertex neigbhor) {
        this.neighbors.add(neigbhor);
    }

    /**
     * Returns the value of data property.
     *
     * @return data
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data property.
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the value of visited property.
     *
     * @return visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the visited property.
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns the value of neighbors property.
     *
     * @return neighbors
     */
    public List<Vertex> getNeighbors() {
        return neighbors;
    }

    /**
     * Sets the neighbors property.
     *
     * @param neighbors
     */
    public void setNeighbors(List<Vertex> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        return data + "";
    }
}