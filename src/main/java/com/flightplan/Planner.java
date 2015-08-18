package com.flightplan;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Planner {

    private static volatile Planner instance;

    private Planner() {
        super();
    }

    public static Planner getInstance() {
        return instance != null ? instance : createInstance();
    }

    // just avoiding "if", assuming replacing it with ternary is okay :-)
    private static synchronized Planner createInstance() {
        instance = (instance == null) ? new Planner() : instance;
        return instance;
    }

    public Queue<String> plan(Strategy strategy, Trip trip) {
        Queue<Airport> queue = new LinkedList<Airport>();
        Queue<Queue<Airport>> graph = new LinkedList<Queue<Airport>>();
        
        queue.add(trip.getDeparture());
        graph.add(queue);           
        List<Queue<Airport>> routes = new LinkedList<Queue<Airport>>();       
        strategy.find(trip, graph, routes);        
        boolean wrkArnd4If = routes.isEmpty() && routes.add(new LinkedList<Airport>());  
        wrkArnd4If = wrkArnd4If || routes.isEmpty();
        return trace(routes);
    }

    private Queue<String> trace(List<Queue<Airport>> routes) {
        StringBuilder sb = new StringBuilder("");
        Queue<String> options = new LinkedList<String>();
        for(Queue<Airport> route : routes){
            sb = new StringBuilder("");
            while (!route.isEmpty()) {
                sb.append(route.poll().getName());
                sb.append(Util.ROUTE_SEPARATOR);
            }
            while (sb.lastIndexOf(Util.ROUTE_SEPARATOR) == sb.length()
                    - Util.ROUTE_SEPARATOR.length()) {
                sb.delete(sb.length() - Util.ROUTE_SEPARATOR.length(), sb.length());
            }
            options.add(sb.toString());
        }
        return options;
    }
}
