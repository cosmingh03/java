public class Airliner extends Aircraft implements PassengerCapable {
    int passengerCapacity;

    public Airliner() {
    }

    public Airliner(String model, int nummber, String name, int passengerCapacity) {
        super(model, nummber, name);
        this.passengerCapacity = passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override

    public String toString() {
        return "Airliner [model=" + model + ", nummber=" + nummber + ", name=" + name + ", passengerCapacity="
                + passengerCapacity + "]";
    }

}