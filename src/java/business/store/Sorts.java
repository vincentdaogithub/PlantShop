package business.store;

public enum Sorts {
    ORDER_TIME("order-time"),
    NAME_ASC("name-asc"),
    NAME_DSC("name-dsc"),
    PRICE_ASC("price-asc"),
    PRICE_DSC("price-dsc");

    private String sort;

    Sorts(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return this.sort;
    }

    public static final Sorts convertStringToSort(String input) {
        if (input == null) {
            return null;
        }

        for (Sorts sort : Sorts.values()) {
            if (sort.getSort().equals(input)) {
                return sort;
            }
        }

        return null;
    }
}
