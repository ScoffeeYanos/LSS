package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader extends Thread{
    byte debugLvl;
    static int time;
    Wache wache;
    Statistics stats;
    public static boolean getNew = true;
    public Reader(byte debugLvl,Wache wache,Statistics stats,int time){
        this.debugLvl = debugLvl;
        this.wache = wache;
        this.stats = stats;
        this.time = time;
    }
    public void run(){
        while(true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = null;
            try {input = reader.readLine();} catch (IOException e) {throw new RuntimeException(e);}
            if(input!=null) {
                String[] splits = input.split(":");
                if (splits[0].equals("DL")) {
                    byte newDebugLvl;
                    try {
                        newDebugLvl = Byte.parseByte(splits[1]);
                    } catch (Exception e) {
                        System.err.println("Invalid DebugLvl");
                        if(debugLvl>=3){
                            System.out.println(splits[1]);
                        }
                        continue;
                    }
                    changeDebugLvl(newDebugLvl);
                } else if (splits[0].equals("Stats")) {
                    if (splits[1].equals("Wache")) {
                        printWacheStatistics();
                    } else if (splits[1].equals("Usage")) {
                        printUsageStatistics();
                    } else {
                        System.err.println("Invalid Statistic");
                    }
                }else if (splits[0].equals("Restart")){
                    if(splits[1].equals("Stats")){
                        restartStatistics();
                    }else{
                        System.err.println("Invalid Thread");
                    }
                }else if(splits[0].equals("SV")){
                    if(splits[1].equals("aLF")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setAvalibleLF(var);
                    }else if(splits[1].equals("aDLK")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setAvalibleDLK(var);
                    }else if(splits[1].equals("aELW")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setAvalibleELW(var);
                    }else if(splits[1].equals("aRW")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setAvalibleRW(var);
                    }

                    else if(splits[1].equals("LF")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setLF(var);
                    }else if(splits[1].equals("DLK")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setDLK(var);
                    }else if(splits[1].equals("ELW")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setELW(var);
                    }else if(splits[1].equals("RW")){
                        byte var;
                        try {var = Byte.parseByte(splits[2]);} catch (Exception e) {System.err.println("Invalid Number");if(debugLvl>=3){System.out.println(splits[1]);}
                            continue;
                        }
                        setRW(var);
                    }
                }else if(splits[0].equals("SC")){
                    if(splits[1].equals("NoNew")){
                        getNew = false;
                    }else if(splits[1].equals("GoNew")){
                        getNew = true;
                    }
                }




                else{
                    System.err.println("Unknown Command");
                }
            }
        }
    }
    public void changeDebugLvl(byte newDebugLvl){
        debugLvl = newDebugLvl;
    }
    public void printWacheStatistics(){
        wache.printStatus();
    }
    public void printUsageStatistics(){
        stats.print();
    }
    public void restartStatistics(){
        stats.run = false;
        while(!stats.dead){
            try {Thread.sleep(time);} catch (InterruptedException e) {throw new RuntimeException(e);}
        }
        Statistics newStats = new Statistics(wache,debugLvl,time);
        newStats.start();
        stats = newStats;
    }
    public void setAvalibleLF(byte var){
        wache.avalibleLF = var;
        System.out.println("AvalibleLF: "+var);
    }
    public void setAvalibleDLK(byte var){
        wache.avalibleDLK = var;
        System.out.println("AvalibleDLK: "+var);
    }
    public void setAvalibleRW(byte var){
        wache.avalibleRW = var;
        System.out.println("AvalibleRW: "+var);
    }
    public void setAvalibleELW(byte var){
        wache.avalibleELW = var;
        System.out.println("AvalibleELW: "+var);
    }

    public void setLF(byte var){
        wache.LF = var;
        System.out.println("LF: "+var);
    }
    public void setDLK(byte var){
        wache.DLK = var;
        System.out.println("DLK: "+var);
    }
    public void setRW(byte var){
        wache.RW = var;
        System.out.println("RW: "+var);
    }
    public void setELW(byte var){
        wache.ELW = var;
        System.out.println("ELW: "+var);
    }
}
