package com.flightplan;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestStrategy extends Strategy {
    
    boolean wrkArnd4If; 

    @Override
    public List<Queue<Airport>> find(Trip trip) {
        Queue<Airport> queue = new LinkedList<Airport>();
        Queue<Queue<Airport>> graph = new LinkedList<Queue<Airport>>();
        List<Queue<Airport>> routes = new LinkedList<Queue<Airport>>();
        
        queue.add(trip.getDeparture());
        graph.add(queue);      
        while (!graph.isEmpty()) {            
            Queue<Airport> currentRoute = graph.poll();            
            wrkArnd4If = reachesDestination(trip, currentRoute) ? routes.add(currentRoute) : false;                     
            wrkArnd4If = reachesDestination(trip, currentRoute) ||                          
            !peekLast(currentRoute).hasConnection() ? false : graph.addAll(createNewRoutes(currentRoute));                     
        }
        wrkArnd4If = routes.isEmpty() ? routes.add(new LinkedList<Airport>()) : false;
        return routes;
    }

    private boolean reachesDestination(Trip trip, Queue<Airport> currentRoute) {
        return trip.hasDestination(peekLast(currentRoute).getName());
    }
    
    private Airport peekLast(Queue<Airport> currentRoute){
        return ((LinkedList<Airport>) currentRoute)
        .getLast();
    }

    private List<Queue<Airport>> createNewRoutes(Queue<Airport> currentRoute) {
        List<Queue<Airport>> routes = new LinkedList<Queue<Airport>>();
        for (Airport dest : peekLast(currentRoute).getConnections()) {
            wrkArnd4If = currentRoute.contains(dest) ? false : 
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
