package com.flightplan;

import java.util.List;
import java.util.Queue;

public class ShortestDFSStrategy extends Strategy {

    @Override    
    public boolean find(Trip trip, Queue<Queue<Airport>> graph, List<Queue<Airport>> routes) {
        wrkArnd4If = graph.isEmpty() || traverse(trip, graph, routes);
        wrkArnd4If = wrkArnd4If || find(trip, graph, routes);
        return wrkArnd4If;
    }
}
