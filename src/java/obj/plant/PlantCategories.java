package obj.plant;

public enum PlantCategories {
    UNDEFINED(0, "Undefined"),
    INDOOR(1, "Indoor"),
    OUTDOOR(2, "Outdoor");

    private final int categoryID;
    private final String categoryName;

    PlantCategories(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public final int getCategoryID() {
        return this.categoryID;
    }

    public final String getCategoryName() {
        return this.categoryName;
    }

    public static final PlantCategories convertIntToCategory(Integer input) {
        if (input == null) {
            return null;
        }

        for (PlantCategories category : PlantCategories.values()) {
            if (category.getCategoryID() == input) {
                return category;
            }
        }

        return null;
    }
}
