package business.store;

public enum Searches {
    NAME("name"),
    PRICE("price");

    private String search;

    Searches(String search) {
        this.search = search;
    }

    public String getSearch() {
        return this.search;
    }

    public static final Searches convertStringToSearch(String input) {
        if (input == null) {
            return null;
        }

        for (Searches search : Searches.values()) {
            if (search.getSearch().equals(input)) {
                return search;
            }
        }

        return null;
    }
}
