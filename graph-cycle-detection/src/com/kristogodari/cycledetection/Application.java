package com.kristogodari.cycledetection;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args){

        Vertex vertex1 = new Vertex("1");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex vertex6 = new Vertex("6");

        vertex1.addNeighbor(vertex2);
        vertex2.addNeighbor(vertex3);
        vertex3.addNeighbor(vertex1);
        vertex4.addNeighbor(vertex1);
        vertex4.addNeighbor(vertex5);
        vertex5.addNeighbor(vertex6);
        vertex6.addNeighbor(vertex4);

        List<Vertex> vertexList = new ArrayList<>();
        vertexList.add(vertex1);
        vertexList.add(vertex2);
        vertexList.add(vertex3);
        vertexList.add(vertex4);
        vertexList.add(vertex5);
        vertexList.add(vertex6);

        CycleDetection cycleDetection = new CycleDetection();
        cycleDetection.detect(vertexList);
    }
}
