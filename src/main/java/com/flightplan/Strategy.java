package com.flightplan;

import java.util.List;
import java.util.Queue;

public abstract class Strategy {
    public abstract List<Queue<Airport>> find(Trip trip);
}
