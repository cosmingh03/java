public class Freighter extends Aircraft implements CargoCapable {
    int cargoCapacity;

    public Freighter() {
    }

    public Freighter(String model, int nummber, String name, int cargoCapacity) {
        super(model, nummber, name);
        this.cargoCapacity = cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String toString() {
        return "Freighter [model=" + model + ", nummber=" + nummber + ", name=" + name + ", cargoCapacity="
                + cargoCapacity + "]";
    }

}
