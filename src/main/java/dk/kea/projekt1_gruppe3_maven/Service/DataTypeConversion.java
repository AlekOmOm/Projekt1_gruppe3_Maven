package dk.kea.projekt1_gruppe3_maven.Service;

public class DataTypeConversion {

    public static int stringToInt(String string, int maxValue) {
        try {
            if (Integer.parseInt(string) > 0 && Integer.parseInt(string) <= maxValue) {
                return Integer.parseInt(string);
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
