package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FuelCalculator {

    public static final String FILE_PATH = "src/main/resources/day1/data";
    public static final String FILE_NOT_FOUND = "Could not find file";

    public static void main(String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        try (Stream<String> modules = Files.lines(Paths.get(FILE_PATH))) {
            int totalWeight = modules.mapToInt(Integer::valueOf)
                    .map(FuelCalculator::fuelWeight)
                    .sum();
            System.out.println(totalWeight);
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    private static void part2() {
        try (Stream<String> modules = Files.lines(Paths.get(FILE_PATH))) {
            int totalFuelWeight = modules.mapToInt(Integer::valueOf)
                    .map(FuelCalculator::fuelForModule)
                    .sum();
            System.out.println(totalFuelWeight);
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    private static int fuelForModule(int moduleWeight) {
        int fuelMass = fuelWeight(moduleWeight);
        return fuelMass <= 0 ? 0 : nextFuelWeightFor(fuelMass);
    }

    private static int nextFuelWeightFor(int weight) {
        return weight <= 0 ? 0 : weight + nextFuelWeightFor(fuelWeight(weight));
    }

    private static int fuelWeight(int weight) {
        return (int) Math.floor(weight / 3d) - 2;
    }
}
