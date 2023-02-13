package security;

public class UserInputSanitizer {
    public static final String sanitizeInput(String input) {
        if (input == null) {
            throw new NullPointerException();
        }

        input = input.trim();
        input.replace("..", "");

        return input;
    }
}
