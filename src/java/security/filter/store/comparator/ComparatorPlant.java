package security.filter.store.comparator;

import java.util.Comparator;
import java.util.Map.Entry;

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

    public static final Comparator<Entry<Plant, Integer>> entryNameAscending() {

        return new Comparator<Entry<Plant, Integer>>() {

            @Override
            public int compare(Entry<Plant, Integer> entry1, Entry<Plant, Integer> entry2) {
                return entry1.getKey().getName().compareTo(entry2.getKey().getName());
            }
        };
    }

    public static final Comparator<Entry<Plant, Integer>> entryNameDescending() {

        return new Comparator<Entry<Plant, Integer>>() {

            @Override
            public int compare(Entry<Plant, Integer> entry1, Entry<Plant, Integer> entry2) {
                return -(entry1.getKey().getName().compareTo(entry2.getKey().getName()));
            }
        };
    }

    public static final Comparator<Entry<Plant, Integer>> entryPriceAscending() {

        return new Comparator<Entry<Plant, Integer>>() {

            @Override
            public int compare(Entry<Plant, Integer> entry1, Entry<Plant, Integer> entry2) {
                return Integer.compare(entry1.getKey().getPrice(), entry2.getKey().getPrice());
            }
        };
    }

    public static final Comparator<Entry<Plant, Integer>> entryPriceDescending() {

        return new Comparator<Entry<Plant, Integer>>() {

            @Override
            public int compare(Entry<Plant, Integer> entry1, Entry<Plant, Integer> entry2) {
                return -(Integer.compare(entry1.getKey().getPrice(), entry2.getKey().getPrice()));
            }
        };
    }
}
