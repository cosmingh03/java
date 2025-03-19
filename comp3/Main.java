import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Airport airport = new Airport("Airport1");
        airport.addRunway(new Runway("Runway1"));
        airport.addRunway(new Runway("Runway2"));

        Flight f1 = new Flight("F1", LocalTime.of(10, 0), LocalTime.of(10, 15));
        Flight f2 = new Flight("F2", LocalTime.of(10, 0), LocalTime.of(10, 15));
        Flight f3 = new Flight("F3", LocalTime.of(11, 0), LocalTime.of(11, 15));
        Flight f4 = new Flight("F4", LocalTime.of(11, 30), LocalTime.of(11, 45));

        Problem problem = new Problem(airport, new ArrayList<>());
        problem.getFlights().add(f1);
        problem.getFlights().add(f2);
        problem.getFlights().add(f3);
        problem.getFlights().add(f4);

        problem.Solve();

        problem.printSolution();

        /*
         * Airliner a1 = new Airliner("A", 1, "Airliner1", 500);
         * Aircraft a2 = new Airliner("B", 2, "Airliner2", 600);
         * Freighter f1 = new Freighter("C", 3, "Freighter1", 1000);
         * Freighter f2 = new Freighter("D", 4, "Freighter2", 2000);
         * Drone d1 = new Drone("E", 5, "Drone1", 100, 50);
         * Drone d2 = new Drone("F", 6, "Drone2", 200, 100);
         * 
         * Aircraft[] aircrafts = { a1, a2, f1, f2, d1, d2 };
         * 
         * ArrayList<Aircraft> transporters = new ArrayList<>();
         * 
         * for (Aircraft aircraft : aircrafts) {
         * if (aircraft instanceof CargoCapable) {
         * transporters.add(aircraft);
         * }
         * }
         * 
         * System.out.println("aicrafts that transport goods:");
         * 
         * for (Aircraft aircraft : transporters) {
         * System.out.println(aircraft);
         * }
         */

    }
}