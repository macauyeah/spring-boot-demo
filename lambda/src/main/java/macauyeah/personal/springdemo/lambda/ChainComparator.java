package macauyeah.personal.springdemo.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChainComparator {
    public static void sortExample(){
        List<Car> cars = new ArrayList<>();
        cars.add(Car.of(300, 2));
        cars.add(Car.of(200, 4));
        cars.add(Car.of(100, 3));
        cars.add(Car.of(200, 1));

        cars.sort(ChainComparator.getOldSchoolComparator());
        ChainComparator.printCarsOrder(cars);
        cars.sort(ChainComparator.getComparatorChain());
        ChainComparator.printCarsOrder(cars);
        cars.sort(ChainComparator.getComparatorChain2());
        ChainComparator.printCarsOrder(cars);
        cars.sort(ChainComparator.getComparatorChain3());
        ChainComparator.printCarsOrder(cars);
    }

    private static void printCarsOrder(List<Car> cars){
        System.out.println("----------");
        cars.forEach(car->{
            System.out.println("carSize:" + car.getSize() + " number of wheels:" + car.getWheels().size());
        });
        System.out.println("----------");
    }

    private static Comparator<Car> getOldSchoolComparator(){
        return (a, b)->{
            Double aCarSize = a.getSize();
            Double bCarSize = b.getSize();
            if (aCarSize.compareTo(bCarSize) != 0) {
                return aCarSize.compareTo(bCarSize);
            } else { // if tied
                Integer aNumOfWheel = a.getWheels().size();
                Integer bNumOfWheel = b.getWheels().size();
                return aNumOfWheel.compareTo(bNumOfWheel);
            }
        };
    }

    private static Comparator<Car> getComparatorCarSize(){
        return (aCar, bCar)->{
            Double aCarSize = aCar.getSize();
            Double bCarSize = bCar.getSize();
            return aCarSize.compareTo(bCarSize);
        };
    }

    private static Comparator<Car> getComparatorNumOfWheels(){
        return (aCar, bCar)->{
            Integer aNumOfWheel = aCar.getWheels().size();
            Integer bNumOfWheel = bCar.getWheels().size();
            return aNumOfWheel.compareTo(bNumOfWheel);
        };
    }

    private static Comparator<Car> getComparatorChain(){
        return ChainComparator.getComparatorCarSize()
            .thenComparing(ChainComparator.getComparatorNumOfWheels());
    }


    private static Comparator<Double> getComparatorDouble(){
        return (aCarSize, bCarSize)->{
            return aCarSize.compareTo(bCarSize);
        };
    }

    private static Comparator<Integer> getComparatorInteger(){
        return (aNumOfWheel, bNumOfWheel)->{
            return aNumOfWheel.compareTo(bNumOfWheel);
        };
    }

    private static Comparator<Car> getComparatorChain2(){
        Comparator<Car> chainedComparator = Comparator.comparing(
            car->car.getSize(), // converter
            ChainComparator.getComparatorDouble() // reuse exisiting comparator
        );
        chainedComparator = chainedComparator.thenComparing(
            car->car.getWheels().size(), // converter
            ChainComparator.getComparatorInteger() // reuse exisiting comparator
        );
        return chainedComparator;
    }

    private static Comparator<Car> getComparatorChain3(){
        Comparator<Car> chainedComparator = Comparator.comparing(
            car->car.getWheels().size(), // converter
            ChainComparator.getComparatorInteger() // reuse exisiting comparator
        );
        chainedComparator = chainedComparator.thenComparing(
            car->car.getSize(), // converter
            ChainComparator.getComparatorDouble() // reuse exisiting comparator
        );
        return chainedComparator;
    }
}
