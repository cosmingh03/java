package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.shortestpath.DijkstraShortestPathDefault;
import org.graph4j.util.Path;

public class Map {
    private final List<Location> locations;
    private final java.util.Map<Location, Integer> locationToIndex;
    private final java.util.Map<Integer, Location> indexToLocation;
    private final Graph<Integer, Integer> graph;

    public Map() {
        this.locations = new ArrayList<>();
        this.locationToIndex = new HashMap<>();
        this.indexToLocation = new HashMap<>();
        this.graph = GraphBuilder.empty().buildGraph();
    }

    public void addLocation(Location loc) {
        int index = locations.size();
        locations.add(loc);
        locationToIndex.put(loc, index);
        indexToLocation.put(index, loc);
        graph.addVertex(index);
    }

    public void connectLocations(Location a, Location b, double time) {
        Integer indexA = locationToIndex.get(a);
        Integer indexB = locationToIndex.get(b);

        graph.addEdge(indexA, indexB, time);

    }

    public List<Location> getLocations() {
        return locations;
    }

    public java.util.Map<Location, Double> getFastestTimesFrom(Location start) {
        Integer startIndex = locationToIndex.get(start);

        DijkstraShortestPathDefault dijkstra = new DijkstraShortestPathDefault(graph, startIndex);
        double[] timesArray = dijkstra.getPathWeights();

        java.util.Map<Location, Double> times = new HashMap<>();
        for (int i = 0; i < timesArray.length; i++) {
            times.put(getLocationAt(i), timesArray[i]);
        }

        return times;
    }

    public Location getLocationAt(int index) {
        return indexToLocation.get(index);
    }

    public int getIndexFor(Location loc) {
        return locationToIndex.get(loc);
    }

    public double getTimeFromAtoB(Location a, Location b) {
        DijkstraShortestPathDefault alg = new DijkstraShortestPathDefault(graph, getIndexFor(a));
        double time = alg.getPathWeight(getIndexFor(b));

        if (Double.isInfinite(time)) {
            return -1;
        }

        int round = (int) (time * 100);
        return (double) round / 100;

    }

    public double getsafeProbability(Location a, Location b) {
        DijkstraShortestPathDefault alg = new DijkstraShortestPathDefault(graph, getIndexFor(a));
        Path path = alg.findPath(getIndexFor(b));

        if (path == null) {
            return -1;
        }

        double probability = 1.0;
        for (int i = 0; i < path.size() - 1; i++) {

            Location loc = getLocationAt(path.get(i));
            if (loc.isEnemy()) {
                probability = probability * 0.25;
            }
            if (loc.isNeutral()) {
                probability = probability * 0.5;
            }

        }
        return probability * 100;

    }

}
