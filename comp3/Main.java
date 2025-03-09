import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Airliner a1 = new Airliner("A", 1, "Airliner1", 500);
        Aircraft a2 = new Airliner("B", 2, "Airliner2", 600);
        Freighter f1 = new Freighter("C", 3, "Freighter1", 1000);
        Freighter f2 = new Freighter("D", 4, "Freighter2", 2000);
        Drone d1 = new Drone("E", 5, "Drone1", 100, 50);
        Drone d2 = new Drone("F", 6, "Drone2", 200, 100);

        Aircraft[] aircrafts = { a1, a2, f1, f2, d1, d2 };

        ArrayList<Aircraft> transporters = new ArrayList<>();

        for (Aircraft aircraft : aircrafts) {
            if (aircraft instanceof CargoCapable) {
                transporters.add(aircraft);
            }
        }

        System.out.println("aicrafts that transport goods:");

        for (Aircraft aircraft : transporters) {
            System.out.println(aircraft);
        }

    }
}