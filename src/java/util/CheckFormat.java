package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFormat {
    public static final boolean check(String stringToCheck, String regex) {
        if (stringToCheck == null || regex == null) {
            throw new NullPointerException();
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToCheck);

        return matcher.find();
    }
}
