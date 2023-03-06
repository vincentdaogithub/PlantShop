package util;

public class UserInput {

    public static final boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static final Integer toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
