package obj.plant;

public enum PlantCategories {
    INDOOR(1, "Indoor"),
    OUTDOOR(2, "Outdoor");

    private int categoryID;
    private String categoryName;

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
}
