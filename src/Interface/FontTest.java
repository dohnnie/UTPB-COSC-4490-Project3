package src.Interface;

import java.awt.*;

public class FontTest {

    private static boolean contains(String[] list, String key) {
        for (String s : list) {
            if (s.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        System.out.println(contains(ge.getAvailableFontFamilyNames(), "Ravie"));
    }
}
