package com.flightplan;

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

    public String plan(Strategy strategy, Trip trip) {
        Queue<Airport> route = strategy.find(trip);
        return trace(route);
    }

    private String trace(Queue<Airport> route) {
        StringBuilder sb = new StringBuilder("");
        while (!route.isEmpty()) {
            sb.append(route.poll().getName());
            sb.append(Util.ROUTE_SEPARATOR);
        }
        while (sb.lastIndexOf(Util.ROUTE_SEPARATOR) == sb.length()
                - Util.ROUTE_SEPARATOR.length()) {
            sb.delete(sb.length() - Util.ROUTE_SEPARATOR.length(), sb.length());
        }
        return sb.toString();
    }
}
