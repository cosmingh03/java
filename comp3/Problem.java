import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
    private Airport airport;
    private List<Flight> flights;
    private Map<Flight, Runway> flightToRunwayMap;

    public Problem() {
    }

    public Problem(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
        this.flightToRunwayMap = new HashMap<>();

    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Airport getAirport() {
        return airport;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void Solve() {
        flights.sort(Comparator.comparing(Flight::getLandingA));

        for (Flight flight : flights) {

            for (Runway runway : airport.getRunways()) {
                if (runway.canAccommodateFlight(flight)) {
                    runway.scheduleFlight(flight);
                    flightToRunwayMap.put(flight, runway);
                    break;
                }
            }

        }

    }

    public Map<Flight, Runway> getFlightToRunwayMap() {
        return flightToRunwayMap;
    }

    public void printSolution() {
        System.out.println("Flight Schedule for " + airport.getName());

        for (Runway runway : airport.getRunways()) {
            System.out.println("\nRunway: " + runway.getId());
            List<Flight> runwayFlights = runway.getScheduledFlights();
            runwayFlights.sort(Comparator.comparing(Flight::getLandingA));

            for (Flight flight : runwayFlights) {
                System.out.println("  " + flight.getId() + ": " + flight.getLandingA() + " - " + flight.getLandingB());
            }
        }
    }

}
