package io.github.macauyeah.springbootdemo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.github.macauyeah.springbootdemo.lambda.FilterList.FilterList;

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
        List<Wheel> wheels = new ArrayList<>(4);
        cars = cars.stream().filter(
            filterCarByWheelSizePredicate(wheels)
        ).toList();
        return cars;
    }

    private static Predicate<Car> filterCarByWheelSizePredicate(List<Wheel> wheels){
        return (car) -> {
            if (car.getWheels().size() == wheels.size()) {
                // many other check logics
                // many other check logics
                // many other check logics
                return true;
            }
            return false;
        };
    }


    // ex6
    public static List<Car> manyListFiltering() {
        List<Wheel> wheels = new ArrayList<>(4);
        wheels = wheels.stream().filter((wheel)->{
            // some logic
            return true;
        }).collect(Collectors.toList());
        List<Car> cars = new ArrayList<>();
        cars = cars.stream().filter(
            filterCarByWheelSizePredicate(wheels)
        ).collect(Collectors.toList());

        // many list filtering will gave you many repeating code like stream(), collect()
        return cars;
    }

    // ex7
    public static List<Car> manyListFilteringByExtendArayList() {
        FilterList<Wheel> wheels = new FilterList<>(4);
        wheels = wheels.filter((wheel)->{
            // some logic
            return true;
        });
        FilterList<Car> cars = new FilterList<>();
        cars = cars.filter(filterCarByWheelSizePredicate(wheels));
        return cars;
    }
}
