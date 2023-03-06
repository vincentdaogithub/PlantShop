package security.filter.store.comparator;

import java.util.Comparator;

import obj.plant.Plant;

public class ComparatorPlant {

    public static final Comparator<Plant> nameAscending() {

        return new Comparator<Plant>() {

            @Override
            public int compare(Plant plant1, Plant plant2) {
                return plant1.getName().compareToIgnoreCase(plant2.getName());
            }
        };
    }

    public static final Comparator<Plant> nameDescending() {

        return new Comparator<Plant>() {

            @Override
            public int compare(Plant plant1, Plant plant2) {
                return -(plant1.getName().compareToIgnoreCase(plant2.getName()));
            }
        };
    }

    public static final Comparator<Plant> priceAscending() {

        return new Comparator<Plant>() {

            @Override
            public int compare(Plant plant1, Plant plant2) {
                return Integer.compare(plant1.getPrice(), plant2.getPrice());
            }
        };
    }

    public static final Comparator<Plant> priceDescending() {

        return new Comparator<Plant>() {

            @Override
            public int compare(Plant plant1, Plant plant2) {
                return -(Integer.compare(plant1.getPrice(), plant2.getPrice()));
            }
        };
    }
}
