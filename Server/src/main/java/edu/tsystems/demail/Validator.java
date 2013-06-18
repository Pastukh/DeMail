package edu.tsystems.demail;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 17.06.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class Validator {
    public boolean checkPassword (char[] pass1, char[] pass2){
        return Arrays.equals(pass1, pass2);
    }

}
