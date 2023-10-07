package org.example;

public class Util {
    public static void sleep(int var) {
        try {
            Thread.sleep(var);
        } catch (InterruptedException e) {
        }
    }
    public static void sleepSec(int var){
        try {
            Thread.sleep(var*1000);
        } catch (InterruptedException e) {
        }
    }
}
