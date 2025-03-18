import java.util.ArrayList;
import java.util.List;

public class Runway {
    private String id;
    private List<Flight> scheduledFlights;

    public Runway() {
    }

    public Runway(String id) {
        this.id = id;
        this.scheduledFlights = new ArrayList<>();
    }

    public Runway(String id, List<Flight> scheduledFlights) {
        this.id = id;
        this.scheduledFlights = scheduledFlights;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScheduledFlights(List<Flight> scheduledFlights) {
        this.scheduledFlights = scheduledFlights;
    }

    public boolean canAccommodateFlight(Flight flight) {
        for (Flight scheduledFlight : scheduledFlights) {
            if (flight.overlapsWithFlight(scheduledFlight)) {
                return false;
            }
        }
        return true;
    }

    public void addFlight(Flight flight) {
        scheduledFlights.add(flight);
    }

    public String getId() {
        return id;
    }

    public List<Flight> getScheduledFlights() {
        return scheduledFlights;
    }

    public void removeFlight(Flight flight) {
        scheduledFlights.remove(flight);
    }

    public void scheduleFlight(Flight flight) {
        scheduledFlights.add(flight);
    }

}
