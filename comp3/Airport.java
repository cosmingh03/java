import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String name;
    private List<Runway> runways;

    public Airport() {
    }

    public Airport(String name) {
        this.name = name;
        this.runways = new ArrayList<>();
    }

    public Airport(String name, List<Runway> runways) {
        this.name = name;
        this.runways = runways;
    }

    public void addRunway(Runway runway) {
        runways.add(runway);
    }

    public List<Runway> getRunways() {
        return runways;
    }

    public String getName() {
        return name;
    }

    public void setId(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRunways(List<Runway> runways) {
        this.runways = runways;
    }

    public boolean canAccommodateFlight(Flight flight) {
        for (Runway runway : runways) {
            if (runway.canAccommodateFlight(flight)) {
                return true;
            }
        }
        return false;
    }

}