package com.flightplan;

public class Trip {

    private Airport departure;
    private String destination;

    public Trip(Airport departure, String destination) {

        while (Util.isEmpty(departure) || Util.isEmpty(destination)) {
            throw new IllegalArgumentException(
                    "Please send both departure & arrival");
        }

        this.departure = departure;
        this.destination = destination;
    }

    public String getOrigin() {
        return departure.getName();
    }

    public String getDestination() {
        return destination;
    }

    public Airport getDeparture() {
        return this.departure;
    }

    public boolean hasOrigin(String origin) {
        return this.departure.getName().equalsIgnoreCase(origin);
    }

    public boolean hasDestination(String destination) {
        return this.destination.equalsIgnoreCase(destination);
    }

}
