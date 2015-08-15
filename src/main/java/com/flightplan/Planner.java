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
        return (instance == null) ? new Planner() : instance;
    }

    public Queue<String> plan(Strategy strategy, Trip trip) {
        List<Queue<Airport>> routes = strategy.find(trip);
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
