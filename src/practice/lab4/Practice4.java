package practice.lab4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Practice4 {

    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }

    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        //readCities用的绝对路径
        Stream<City> cities = readCities("F:\\Sustech-CS209-java2\\src\\practice\\lab4\\cities.txt");
        // Q1: count how many cities there are for each state
        // TODO: Map<String, Long> cityCountPerState = ...
        System.out.println("# of cities per state:");
        Map<String, Long> cityCountPerstate = cities.map(City::getState).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(cityCountPerstate);
        System.out.println();

        cities = readCities("F:\\Sustech-CS209-java2\\src\\practice\\lab4\\cities.txt");
        // Q2: count the total population for each state
        // TODO: Map<String, Integer> statePopulation = ...
        System.out.println("population per state:");
        Map<String, Integer> statePopulation = cities.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println(statePopulation);
        System.out.println();

        cities = readCities("F:\\Sustech-CS209-java2\\src\\practice\\lab4\\cities.txt");
        // Q3: for each state, get the set of cities with >500,000 population
        // TODO: Map<String, Set<City>> largeCitiesByState = ...
        System.out.println("cities with >500,000 population for each state:");
        Map<String, Set<City>> largeCitiesByState = cities.collect(Collectors.groupingBy(City::getState, Collectors.filtering(s -> s.getPopulation() > 500000, Collectors.toSet())));
        largeCitiesByState.forEach((key, value) -> {
            System.out.print(key + ": [");
            int i = 1;
            for (City city : value) {
                System.out.printf("City{name='%s', state='%s', population=%d}", city.getName(), city.getState(), city.getPopulation());
                if (i < value.size()) {
                    System.out.print(", ");
                }
                i++;
            }
            System.out.print("]\n");

        });
    }


}
