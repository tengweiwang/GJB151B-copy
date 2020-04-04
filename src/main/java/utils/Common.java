package utils;

/**
 * Created by Creaink on 2018/8/21.
 */
public class Common {
    public boolean isNullOrEmpty(String input) {
        if(input == null)
            return true;
        input = input.trim();
        return input.length() ==0;
    }
}
