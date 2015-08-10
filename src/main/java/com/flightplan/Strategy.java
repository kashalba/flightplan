package com.flightplan;

import java.util.Queue;

public abstract class Strategy {
    public abstract Queue<Airport> find(Trip trip);
}
