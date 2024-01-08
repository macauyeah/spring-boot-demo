package io.github.macauyeah.springbootdemo.lambda;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ChainComparatorTests {
    @Test
    void testGenerateListOfCar() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.of(300, 2));
        cars.add(Car.of(200, 4));
        cars.add(Car.of(100, 3));
        cars.add(Car.of(200, 1));

        cars.sort(ChainComparator.getOldSchoolComparator());
        assert cars.get(0).getSize() == 100.0;
        assert cars.get(0).getWheels().size() == 3;
        assert cars.get(1).getSize() == 200.0;
        assert cars.get(1).getWheels().size() == 1;
        assert cars.get(2).getSize() == 200.0;
        assert cars.get(2).getWheels().size() == 4;
        assert cars.get(3).getSize() == 300.0;
        assert cars.get(3).getWheels().size() == 2;

        cars.sort(ChainComparator.getComparatorChain());
        assert cars.get(0).getSize() == 100.0;
        assert cars.get(0).getWheels().size() == 3;
        assert cars.get(1).getSize() == 200.0;
        assert cars.get(1).getWheels().size() == 1;
        assert cars.get(2).getSize() == 200.0;
        assert cars.get(2).getWheels().size() == 4;
        assert cars.get(3).getSize() == 300.0;
        assert cars.get(3).getWheels().size() == 2;

        cars.sort(ChainComparator.getComparatorChain2());
        assert cars.get(0).getSize() == 100.0;
        assert cars.get(0).getWheels().size() == 3;
        assert cars.get(1).getSize() == 200.0;
        assert cars.get(1).getWheels().size() == 1;
        assert cars.get(2).getSize() == 200.0;
        assert cars.get(2).getWheels().size() == 4;
        assert cars.get(3).getSize() == 300.0;
        assert cars.get(3).getWheels().size() == 2;

        cars.sort(ChainComparator.getComparatorChain3());

        assert cars.get(0).getWheels().size() == 1;
        assert cars.get(0).getSize() == 200.0;
        assert cars.get(1).getWheels().size() == 2;
        assert cars.get(1).getSize() == 300.0;
        assert cars.get(2).getWheels().size() == 3;
        assert cars.get(2).getSize() == 100.0;
        assert cars.get(3).getWheels().size() == 4;
        assert cars.get(3).getSize() == 200.0;

    }
}
