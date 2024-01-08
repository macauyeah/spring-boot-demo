package io.github.macauyeah.springbootdemo.lambda;

import java.util.ArrayList;
import java.util.List;

public class ListOfCar {
    private List<Car> tempCars = new ArrayList<>();

    public List<Car> getTempCars() {
        return tempCars;
    }

    public void setTempCars(List<Car> tempCars) {
        this.tempCars = tempCars;
    }
    
    public List<Car> filterCarsByWheelsSize(int targetSize) {
        List<Car> passTestCars = new ArrayList<>();
        for (Car car : tempCars) {
            if (car.getWheels().size() == targetSize) {
                // many other check logics
                // many other check logics
                // many other check logics
                passTestCars.add(car);
            }
        }
        return passTestCars;
    }
}
