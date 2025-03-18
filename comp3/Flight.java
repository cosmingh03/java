
import java.time.LocalTime;

public class Flight {
    private String id;
    private LocalTime landingA;
    private LocalTime landingB;

    public Flight() {
    }

    public Flight(String id, LocalTime landingA, LocalTime landingB) {
        this.id = id;
        this.landingA = landingA;
        this.landingB = landingB;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanding(LocalTime landingA, LocalTime landingB) {
        this.landingA = landingA;
        this.landingB = landingB;
    }

    public String getId() {
        return id;
    }

    public LocalTime getLandingA() {
        return landingA;
    }

    public LocalTime getLandingB() {
        return landingB;
    }

    public boolean overlapsWithFlight(Flight other) {

        return !(landingB.isBefore(other.landingA) ||
                landingA.isAfter(other.landingB));
    }

}