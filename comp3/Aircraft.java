abstract class Aircraft {
    protected String model;
    protected int nummber;
    protected String name;

    public Aircraft() {
    };

    public Aircraft(String model, int nummber, String name) {
        this.model = model;
        this.nummber = nummber;
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNummber(int nummber) {
        this.nummber = nummber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public int getNummber() {
        return nummber;
    }

    public String getName() {
        return name;
    }

}
