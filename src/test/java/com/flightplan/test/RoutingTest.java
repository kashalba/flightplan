package com.flightplan.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.flightplan.Airport;
import com.flightplan.Planner;
import com.flightplan.StrategyFactoryEnum;
import com.flightplan.Trip;

public class RoutingTest {

    Planner planner;
    Map<String, Airport> airports;

    @Test
    public void twoStopsFromHtoE() {
        Trip trip = new Trip(airports.get("h"), "e");
        assertEquals(
                "h->b->c->e",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void nonStopFromCtoE() {
        Trip trip = new Trip(airports.get("c"), "e");
        assertEquals(
                "c->e",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void nonStopFromAtoF() {
        Trip trip = new Trip(airports.get("a"), "f");
        assertEquals(
                "a->f",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void oneStopFromCtoB() {
        Trip trip = new Trip(airports.get("c"), "b");
        assertEquals(
                "c->e->b",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void threeStopsFromCtoF() {
        Trip trip = new Trip(airports.get("c"), "f");
        assertEquals(
                "c->e->b->a->f",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void threeStopsFromDtoF() {
        Trip trip = new Trip(airports.get("d"), "f");
        assertEquals(
                "d->e->b->a->f",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void twoStopsFromHtoD() {
        Trip trip = new Trip(airports.get("h"), "d");
        assertEquals(
                "h->b->c->d",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void twoStopsFromDtoC() {
        Trip trip = new Trip(airports.get("d"), "c");
        assertEquals(
                "d->e->b->c",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void twoStopsFromHtoF() {
        Trip trip = new Trip(airports.get("h"), "f");
        assertEquals(
                "h->b->a->f",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void noFlightFromGtoH() {
        Trip trip = new Trip(airports.get("g"), "h");
        assertEquals(
                "",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void noFlightFromBtoG() {
        Trip trip = new Trip(airports.get("b"), "g");
        assertEquals(
                "",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void noFlightFromFtoH() {
        Trip trip = new Trip(airports.get("f"), "h");
        assertEquals(
                "",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Test
    public void noFlightFromBtoH() {
        Trip trip = new Trip(airports.get("b"), "h");
        assertEquals(
                "",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }
    
    @Test
    public void nonStopFromMSPtoDBU() {
        Trip trip = new Trip(airports.get("MSP"), "DBU");
        assertEquals(
                "MSP->DBU",
                Planner.getInstance().plan(
                        StrategyFactoryEnum.SHORTEST.getInstance(), trip));
    }

    @Before
    public void setupAirports() {

        String[] ports = { "h", "b", "a", "c", "e", "d", "f", "g", "MSP", "FFK", "AMS", "DBU" };
        String[][] connections = { { "h", "b" }, { "b", "c" }, { "b", "a" },
                { "a", "f" }, { "c", "d" }, { "c", "e" }, { "e", "b" },
                { "d", "e" }, {"MSP", "FFK"}, {"MSP", "AMS"}, {"MSP", "DBU"}, {"FFK", "AMS"},
                {"AMS", "FFK"}, {"AMS","MSP"}, {"AMS", "DBU"}, {"DBU", "AMS"}, {"DBU", "MSP"}};
        airports = new HashMap<String, Airport>();
        Arrays.stream(ports).forEach(p -> airports.put(p, new Airport(p)));
        Arrays.stream(connections).forEach(
                c -> airports.get(c[0]).addConnection(airports.get(c[1])));
    }

}
