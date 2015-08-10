package com.flightplan;

import java.util.HashSet;
import java.util.Set;

public class Airport {

    private String name;
    private Set<Airport> connections;

    public Airport(String name) {
        while (Util.isEmpty(name)) {
            throw new IllegalArgumentException(
                    "Please enter a valid Airport name");
        }
        this.name = name;
        connections = new HashSet<Airport>();
    }

    public void addConnection(Airport destinationAirport) {
        while (Util.isEmpty(destinationAirport)) {
            throw new IllegalArgumentException(
                    "Please send a valid destination airport");
        }
        this.connections.add(destinationAirport);
    }

    public boolean equalsName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public boolean equalsAirport(Airport airport) {
        return airport != null && this.name.equals(airport.getName());
    }

    public String getName() {
        return this.name;
    }

    public Set<Airport> getConnections() {
        return connections;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return Util.isEmpty(obj) ? null
                : obj instanceof Airport ? equalsAirport((Airport) obj)
                        : obj instanceof String ? equalsName((String) obj)
                                : super.equals(obj);
    }

}
