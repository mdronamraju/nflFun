package org.dronamraju.nfl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mdronamr on 9/19/16.
 */
public class Validator {
    private static final String EMAIL_REGEX="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Matcher matcher;
    private static Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
