package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFormat {

    public static final boolean checkSensitive(String stringToCheck, String regex) {

        if (stringToCheck == null || regex == null) {
            throw new NullPointerException();
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToCheck);

        return matcher.find();
    }

    public static final boolean checkInsensitive(String stringToCheck, String regex) {

        if (stringToCheck == null || regex == null) {
            throw new NullPointerException();
        }

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(stringToCheck);

        return matcher.find();
    }
}
