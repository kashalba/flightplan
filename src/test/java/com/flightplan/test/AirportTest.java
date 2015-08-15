package com.flightplan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.flightplan.Airport;

public class AirportTest {

    private String departure;
    private String arrival;
    private Airport airport;
    private Airport connection;

    @Before
    public void setup() {
        departure = "h";
        arrival = "d";
        airport = new Airport(departure);
        connection = new Airport(arrival);
        airport.addConnection(connection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorOutOnEmptyName() {
        new Airport(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorOutOnMissingName() {
        new Airport("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorOutOnMissingConnection() {
        new Airport("a").addConnection(null);
    }

    @Test
    public void shouldBeAbleToAddAndGetAConnection() {
        assertTrue(airport.getConnections().contains(connection));
    }

    @Test
    public void shouldNotGetANonConnection() {
        assertTrue(!airport.getConnections().contains(airport));
    }
    
    @Test
    public void hasConnection() {
        assertTrue(airport.hasConnection());
    }
    
    @Test
    public void hasNoConnection() {
        assertTrue(!(new Airport("a").hasConnection()));
    }

    @Test
    public void shouldMatchName() {
        assertTrue(airport.equals(departure));
    }

    @Test
    public void shouldNotMatchDifferentName() {
        assertTrue(!airport.equals(arrival));
    }

    @Test
    public void shouldMatchSameNameAirport() {
        Airport other = new Airport(departure);
        assertTrue(airport.equals(other));
    }

    @Test
    public void shouldNotMatchDifferentAirport() {
        Airport other = new Airport(arrival);
        assertTrue(!airport.equals(other));
    }

    @Test
    public void shouldLogName() {
        assertEquals(departure, airport.toString());
    }

}
