package reWritten.utils;

public class Utils {

    public static boolean isInteger(String instruction){
        if (instruction == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(instruction);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
