package macauyeah.personal.springdemo.lambda;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private List<Wheel> wheels = new ArrayList<>();

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public void addWheel(Wheel wheel) {
        this.getWheels().add(wheel);
    }

}
