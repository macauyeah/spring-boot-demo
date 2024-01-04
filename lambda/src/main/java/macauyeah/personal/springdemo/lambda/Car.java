package macauyeah.personal.springdemo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Car {
    private List<Wheel> wheels = new ArrayList<>();
    private double size;

    public static Car of(double carSize, int numberOfWheels) {
        Car car = new Car();
        car.size = carSize;
        for (int i = 0; i < numberOfWheels; i++) {
            car.getWheels().add(new Wheel());
        }
        return car;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public void addWheel(Wheel wheel) {
        this.getWheels().add(wheel);
    }

    // open converter?
    public <R> R map(Function<Car, R> functional) {
        return functional.apply(this);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
