package edu.tsystems.demail;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 09.06.13
 * Time: 22:28
 */
public class Validator {
    public static boolean checkPassword (char[] pass1, char[] pass2){
        return Arrays.equals(pass1, pass2);
    }
    private static boolean regularChecking(String str, String regular){
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }
    public static boolean userNameChecking(String user){
        boolean ret = user.length() >= 4 &&
                user.length() <= 30 &&
                regularChecking(user, "[a-zA-Z]{1}\\w*");
        return ret;
    }

}
