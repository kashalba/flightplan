package com.flightplan;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Strategy {
    
    protected boolean wrkArnd4If; 
    
    public abstract boolean find(Trip trip, Queue<Queue<Airport>> graph, List<Queue<Airport>> routes);
    
    protected boolean traverse(Trip trip, Queue<Queue<Airport>> graph, List<Queue<Airport>> routes){
        Queue<Airport> currentRoute = graph.poll();            
        wrkArnd4If = reachesDestination(trip, currentRoute) && routes.add(currentRoute);                     
        wrkArnd4If = (reachesDestination(trip, currentRoute) ||                          
        !peekLast(currentRoute).hasConnection()) || 
            graph.addAll(createNewRoutes(currentRoute));
        return false;
    } 
    
    protected boolean reachesDestination(Trip trip, Queue<Airport> currentRoute) {
        return trip.hasDestination(peekLast(currentRoute).getName());
    }
    
    protected Airport peekLast(Queue<Airport> currentRoute){
        return ((LinkedList<Airport>) currentRoute)
        .getLast();
    }

    protected List<Queue<Airport>> createNewRoutes(Queue<Airport> currentRoute) {
        List<Queue<Airport>> routes = new LinkedList<Queue<Airport>>();
        for (Airport dest : peekLast(currentRoute).getConnections()) {
            wrkArnd4If = currentRoute.contains(dest) || 
            routes.add(createNotVisitedRoute(currentRoute, dest));
        }
        return routes;
    }

    private Queue<Airport> createNotVisitedRoute(Queue<Airport> currentRoute,
            Airport dest) {
        Queue<Airport> route = new LinkedList<Airport>();
            route.addAll(currentRoute);
            route.add(dest);
        return route;
    }
}
