package business.sort;

public enum PriceRanges {
    BELOW_5("below-5"),
    FIVE_TO_10("5-10"),
    TEN_TO_15("10-15"),
    ABOVE_15("above-15");

    private String range;

    PriceRanges(String range) {
        this.range = range;
    }

    public String getRange() {
        return this.range;
    }

    public static final PriceRanges convertStringToRange(String input) {
        if (input == null) {
            return null;
        }

        for (PriceRanges range : PriceRanges.values()) {
            if (range.getRange().equals(input)) {
                return range;
            }
        }

        return null;
    }
}
