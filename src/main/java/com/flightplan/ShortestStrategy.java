package com.flightplan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestStrategy extends Strategy {

    @Override
    public Queue<Airport> find(Trip trip) {

        Queue<Airport> queue = new LinkedList<Airport>();
        Queue<Queue<Airport>> graph = new LinkedList<Queue<Airport>>();
        List<Airport> visitedPorts = new ArrayList<Airport>();
        Queue<Airport> route = new LinkedList<Airport>();
        boolean reachesDestination = false;
        Queue<Airport> parentLinkedList = null;

        queue.add(trip.getDeparture());
        graph.add(queue);
        visitedPorts.add(queue.peek());

        while (!graph.isEmpty() && !reachesDestination) {
            parentLinkedList = (LinkedList<Airport>) graph.poll();
            for (Airport dest : ((LinkedList<Airport>) parentLinkedList)
                    .getLast().getConnections()) {
                while (!visitedPorts.contains(dest) && !reachesDestination) {
                    reachesDestination = reachesDestination
                            || trip.hasDestination(dest.getName());
                    route = createNewRoute(parentLinkedList, dest);
                    graph.add(route);
                    visitedPorts.add(dest);
                }
            }
        }
        return reachesDestination ? route : new LinkedList<Airport>();
    }

    private Queue<Airport> createNewRoute(Queue<Airport> parentLinkedList,
            Airport halt) {
        Queue<Airport> childLinkedList = new LinkedList<Airport>();
        childLinkedList.addAll(parentLinkedList);
        childLinkedList.add(halt);
        return childLinkedList;
    }
}
