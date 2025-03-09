public class Drone extends Aircraft implements CargoCapable {
    private int battery;
    private int cargoCapacity;

    public Drone() {
    }

    public Drone(String model, int nummber, String name, int battery, int cargoCapacity) {
        super(model, nummber, name);
        this.battery = battery;
        this.cargoCapacity = cargoCapacity;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    @Override
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public int getBattery() {
        return battery;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String toString() {
        return "Drone [model=" + model + ", nummber=" + nummber + ", name=" + name + ", battery=" + battery
                + ", cargoCapacity=" + cargoCapacity + "]";
    }

}
