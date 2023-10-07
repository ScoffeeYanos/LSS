package org.example;

import java.util.*;

public class Fahrzeuge {
    private static Object[][] fahrzeuge = new Object[4][3];
    protected static void init(){
        fahrzeuge[0][2] = "LF";
        fahrzeuge[1][2] = "DLK";
        fahrzeuge[2][2] = "RW";
        fahrzeuge[3][2] = "ELW";
        for(int i = 0;i<fahrzeuge.length;i++){
            fahrzeuge[i][0] = 0;
            fahrzeuge[i][1] = 0;
        }
    }
    protected static Object[][] getFahrzeuge(){
        Object[][] copy = Arrays.stream(fahrzeuge).map(Object[]::clone).toArray(Object[][]::new);
        return copy;
    }
}
