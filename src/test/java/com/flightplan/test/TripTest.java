package com.flightplan.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.flightplan.Airport;
import com.flightplan.Trip;

public class TripTest {

    private Trip trip;
    private String departure;
    private String arrival;

    @Before
    public void setup() {
        departure = "h";
        arrival = "d";
        Airport h = new Airport(departure);
        trip = new Trip(h, arrival);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorOutOnEmptyArrival() {
        new Trip(new Airport("d"), "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorOutOnMissingArrival() {
        new Trip(new Airport("d"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorOutOnEmptyDeparture() {
        new Trip(null, "b");
    }

    @Test
    public void shouldHaveCorrectDestination() {
        assertTrue(trip.hasDestination(arrival));
    }

    @Test
    public void shouldNotHaveIncorrectDestination() {
        assertFalse(trip.hasDestination(departure));
    }

    @Test
    public void shouldHaveCorrectOrigin() {
        assertTrue(trip.hasOrigin(departure));
    }

    @Test
    public void shouldNotHaveIncorrectOrigin() {
        assertFalse(trip.hasOrigin(arrival));
    }

}
