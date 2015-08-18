package com.flightplan;

import java.util.List;
import java.util.Queue;

public class ShortestBFSStrategy extends Strategy {

    @Override    
    public boolean find(Trip trip, Queue<Queue<Airport>> graph, List<Queue<Airport>> routes) {
        while (!graph.isEmpty()) { 
            traverse(trip, graph, routes);
        }
        return wrkArnd4If;
    }    
}
