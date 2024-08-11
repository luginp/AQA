package pageobject.Models;


public class Car {
    private String year;
    private String model;
    private String make;
    private String engine;
    private String transmission;

    public Car(String carEngine, String carTransmission) {
        this.engine = carEngine;
        this.transmission = carTransmission;
    }

    public Car() {

    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getFullCarName() {
        return year + " " + make + " " + model;
    }

    public String getEngine() {
        return engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return transmission.contains(car.transmission) &&
                engine.contains(car.engine);

    }
}

