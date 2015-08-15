package com.flightplan.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals("h->b->c->e",queue.poll());
    }

    @Test
    public void nonStopFromCtoE() {
        Trip trip = new Trip(airports.get("c"), "e");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals("c->e",queue.poll());
    }

    @Test
    public void nonStopFromAtoF() {
        Trip trip = new Trip(airports.get("a"), "f");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "a->f",queue.poll());
    }

    @Test
    public void oneStopFromCtoB() {
        Trip trip = new Trip(airports.get("c"), "b");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "c->e->b",
                queue.poll());
    }

    @Test
    public void threeStopsFromCtoF() {
        Trip trip = new Trip(airports.get("c"), "f");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "c->e->b->a->f",
                queue.poll());
    }

    @Test
    public void threeStopsFromDtoF() {
        Trip trip = new Trip(airports.get("d"), "f");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "d->e->b->a->f",
                queue.poll());
    }

    @Test
    public void twoStopsFromHtoD() {
        Trip trip = new Trip(airports.get("h"), "d");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "h->b->c->d",
                queue.poll());
    }

    @Test
    public void twoStopsFromDtoC() {
        Trip trip = new Trip(airports.get("d"), "c");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "d->e->b->c",
                queue.poll());
    }

    @Test
    public void twoStopsFromHtoF() {
        Trip trip = new Trip(airports.get("h"), "f");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "h->b->a->f",
                queue.poll());
    }

    @Test
    public void noFlightFromGtoH() {
        Trip trip = new Trip(airports.get("g"), "h");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "",
                queue.poll());
    }

    @Test
    public void noFlightFromBtoG() {
        Trip trip = new Trip(airports.get("b"), "g");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "",
                queue.poll());
    }

    @Test
    public void noFlightFromFtoH() {
        Trip trip = new Trip(airports.get("f"), "h");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "",
                queue.poll());
    }

    @Test
    public void noFlightFromBtoH() {
        Trip trip = new Trip(airports.get("b"), "h");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "",
                queue.poll());
    }
    
    @Test
    public void nonStopFromMSPtoDBU() {
        Trip trip = new Trip(airports.get("MSP"), "DBU");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals(
                "MSP->DBU",queue.poll());
    }
    
    @Test
    public void topThreefromMSPtoDBU() {
        Trip trip = new Trip(airports.get("MSP"), "DBU");
        Queue<String> queue = Planner.getInstance().plan(StrategyFactoryEnum.SHORTEST.getInstance(), trip);
        assertEquals("MSP->DBU", queue.poll());
        assertEquals("MSP->AMS->DBU", queue.poll());
        assertEquals("MSP->FFK->AMS->DBU", queue.poll());
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
