package org.example;
public class Main {
    static Config config;
    public static void main(String[] args) throws Throwable {
        config = new Config(args[0],args[1]);
    }
}