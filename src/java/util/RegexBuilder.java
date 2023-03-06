package util;

import java.util.StringTokenizer;

public class RegexBuilder {
    
    private final String NON_CHARACTER_REGEX = "[^a-zA-Z]";
    private StringBuilder builder;

    public RegexBuilder() {
        this.builder = new StringBuilder();
    }

    public final String build() {
        return this.builder.toString();
    }

    private final void destroy() {
        this.builder = new StringBuilder();
    }

    public final RegexBuilder generateSearchRegex(String searchString) {

        destroy();

        if (searchString == null || searchString.trim().isEmpty()) {
            this.builder.append('.');
            return this;
        }

        StringTokenizer tokenizer = new StringTokenizer(searchString, " ");
        boolean isFirstWord = true;

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            word = word.replaceAll(NON_CHARACTER_REGEX, "");

            if (word.length() <= 0) {
                continue;
            }

            if (isFirstWord) {
                isFirstWord = false;
            } else {
                this.builder.append('|');
            }

            builder.append('(');

            for (int i = 0; i < word.length() - 1; i++) {
                builder.append(word.charAt(i));
                builder.append(".+");
            }

            builder.append(word.charAt(word.length() - 1));
            builder.append(')');
        }

        return this;
    }
}
