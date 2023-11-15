package macauyeah.personal.springdemo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Factory {
    // ex1
    public static List<Car> generateListOfCarByForLoop() {
        List<Car> tempCars = new ArrayList<>();
        // many other logic
        // many other logic
        // many other logic
        List<Car> passTestCars = new ArrayList<>();
        for (Car car : tempCars) {
            if (car.getWheels().size() == 4) {
                // many other check logics
                // many other check logics
                // many other check logics
                passTestCars.add(car);
            }
        }
        return passTestCars;
    }

    // ex2
    public static List<Car> generateListOfCarByLamda() {
        List<Car> cars = new ArrayList<>();
        // many other logic
        // many other logic
        // many other logic
        cars = cars.stream().filter((car) -> {
            if (car.getWheels().size() == 4) {
                // many other check logics
                // many other check logics
                // many other check logics
                return true;
            }
            return false;
        }).toList();
        return cars;
    }

    // ex3
    public static List<Car> generateListOfCarByForLoopFunction() {
        List<Car> tempCars = new ArrayList<>();
        // many other logic
        // many other logic
        // many other logic
        List<Car> passTestCars = filterCarsByWheelsSize(tempCars, 4);
        return passTestCars;
    }

    private static List<Car> filterCarsByWheelsSize(List<Car> originalList, int targetSize) {
        List<Car> passTestCars = new ArrayList<>();
        for (Car car : originalList) {
            if (car.getWheels().size() == targetSize) {
                // many other check logics
                // many other check logics
                // many other check logics
                passTestCars.add(car);
            }
        }
        return passTestCars;
    }

    // ex4
    public static List<Car> generateListOfCarByClassWrap() {
        ListOfCar tempCars = new ListOfCar();
        // many other logic
        // many other logic
        // many other logic
        List<Car> passTestCars = tempCars.filterCarsByWheelsSize(4);
        return passTestCars;
    }

    // ex5
    public static List<Car> generateListOfCarByLamdaComposition() {
        List<Car> cars = new ArrayList<>();
        // many other logic
        // many other logic
        // many other logic
        cars = cars.stream().filter(
            filterCarByWheelSizePredicate()
        ).toList();
        return cars;
    }

    private static Predicate<Car> filterCarByWheelSizePredicate(){
        return (car) -> {
            if (car.getWheels().size() == 4) {
                // many other check logics
                // many other check logics
                // many other check logics
                return true;
            }
            return false;
        };
    }
}
