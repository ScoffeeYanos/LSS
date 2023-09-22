package org.example;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.LinkedList;

public class Main {
    static int time;
    static byte debugLvl;
    static WebDriver driver;
    static String mainWindowHandle;
    public static Wache wache;
    public static LinkedList alarme;
    public static boolean getNew = true;
    public static void main(String[] args) throws MissingMission {
        time = Integer.parseInt(args[3]);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.leitstellenspiel.de/users/sign_in");
        mainWindowHandle = driver.getWindowHandle();
        login(args[0],args[1]);
        debugLvl = Byte.parseByte(args[2]);
        wache = new Wache(Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]),Integer.parseInt(args[7]),Integer.parseInt(args[8]),Integer.parseInt(args[9]),Integer.parseInt(args[10]),Integer.parseInt(args[11]),Integer.parseInt(args[12]),Integer.parseInt(args[13]));
        alarmWindow();
        alarme = new LinkedList();
        Statistics stats = new Statistics(wache,debugLvl,time);
        stats.start();
        Reader read = new Reader(debugLvl,wache,stats,time);
        read.start();
        if(debugLvl>=1){
            System.out.println("--------------------Setup Finished--------------------");
        }
        while(true){
            if(read.debugLvl!=debugLvl){
                debugLvl=read.debugLvl;
                stats.debugLvl = debugLvl;
                System.err.println("New DebugLvl: "+debugLvl);
            }
            if(read.stats!=stats){
                stats=read.stats;
                System.err.println("Restart:Stats");
            }
            getNew = read.getNew;
            if(alarme.isEmpty()){
                Alarm var = new Alarm(driver,wache,time);
                if(debugLvl>=3){
                    System.out.println("---New Queue Created---");
                }
                try {Thread.sleep(time);} catch (Exception f){};
                alarme.add(var);
                try{var.alarmieren();}catch (MissingVehicle e){
                    if(debugLvl>=3){
                        System.out.println("Alarmierung Fehlgeschlagen | "+"LF: "+wache.avalibleLF+"/"+wache.LF+" DLK: "+wache.avalibleDLK+"/"+wache.DLK);
                    }
                    continue;
                }
                if(debugLvl>=2){
                    System.out.println("Neue Alarmierung: "+var.id+" "+var.missionId+" "+var.missionType.name);
                }
            }else{
                driver.get("https://www.leitstellenspiel.de/missions/"+((Alarm) alarme.getFirst()).id);
                if(debugLvl>=4){
                    System.out.println("GOTO: First Element");
                }
                try {Thread.sleep(time);} catch (Exception f){};
                clearQueue();
                try{reCheck();}catch (Exception eGetNew){}
                if(getNew){
                    try{getNew();}catch (Exception eGetNew){}
                }
                if(debugLvl>=3){
                    System.out.println("Sleep 5s");
                }
                try {Thread.sleep(time*25);} catch (Exception f){};
            }
        }
    }
    public static void login(String login,String pw){
        if(login.equals("0")&&pw.equals("0")){
            while(true){
                try{ WebElement var = var = driver.findElement(By.cssSelector("#new_user > input")); }catch (Exception e){
                    break;
                }
            }
            return;
        }
        WebElement var;
        var = driver.findElement(By.cssSelector("#user_email"));
        var.sendKeys(login);
        var = driver.findElement(By.cssSelector("#user_password"));
        var.sendKeys(pw);
        var = driver.findElement(By.cssSelector("#new_user > input"));
        var.click();
        try {Thread.sleep(time*25);} catch (Exception e){};


    }
    public static void alarmWindow(){
        WebElement var;
        var = driver.findElement(By.partialLinkText("Alarm"));
        String link = var.getAttribute("href");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(link);
        try {Thread.sleep(time*25);} catch (Exception e){};
    }
    public static void reCheck() throws MissingMission {
        if(debugLvl>=4){
            System.out.println("START: RECHECK");
        }
        int i=0;
        while(i<alarme.size()){
            Alarm orig =(Alarm) alarme.get(i);
            driver.get("https://www.leitstellenspiel.de/missions/"+orig.id);
            try {Thread.sleep(time*3);} catch (Exception e){};
            Alarm var = new Alarm(driver,wache,time);
            if(orig.missionId.equals(var.missionId)){
                i++;
                continue;
            }else{
                if(debugLvl>=3){
                    System.err.println("---Nachforderung---");
                }
                Mission sub = Alarm.getMissionType(orig.missionId);
                var.missionType.substract(sub);
                if(debugLvl>=4){
                    var.missionType.printStats();
                }
                try{var.alarmieren();}catch (MissingVehicle e){
                    if(debugLvl>=3){
                        System.out.println("Alarmierung Fehlgeschlagen | "+"LF: "+wache.avalibleLF+"/"+wache.LF+" DLK: "+wache.avalibleDLK+"/"+wache.DLK);
                    }
                    i++;
                    continue;
                }
                if(debugLvl>=2){
                    System.out.println("RE Alarmierung: "+var.id+" "+var.missionId+" "+var.missionType.name);
                }
                alarme.set(i,var);
                System.out.println("");
                i++;
            }
        }
    }
    public static void clearQueue() throws MissingMission {
        if(debugLvl>=4){
            System.out.println("START: CLEARING");
        }
        int i = 0;
        while(i<alarme.size()){
            driver.get("https://www.leitstellenspiel.de/missions/"+((Alarm) alarme.get(i)).id);
            try {Thread.sleep(time*3);} catch (Exception e){};
            try{
                WebElement a = driver.findElement(By.partialLinkText("Alarmieren"));
            }catch (Exception e){
                if(debugLvl>=3){
                    System.out.println("Removing Element");
                }
                Mission mis = ((Alarm) alarme.get(i)).missionType;
                Mission var = Alarm.getMissionType(((Alarm) alarme.get(i)).missionId);
                mis.substract(var);
                while(mis.LF<0){
                    wache.avalibleLF= (byte) (wache.avalibleLF+1);
                    mis.LF++;
                    if(debugLvl>=3){
                        System.out.println("REGAINED:LF");
                    }
                }
                while(mis.DLK<0){
                    wache.avalibleDLK= (byte) (wache.avalibleDLK+1);
                    mis.DLK++;
                    if(debugLvl>=3){
                        System.out.println("REGAINED:DLK");
                    }
                }
                while(mis.ELW<0){
                    wache.avalibleELW= (byte) (wache.avalibleELW+1);
                    mis.ELW++;
                    if(debugLvl>=3){
                        System.out.println("REGAINED:ELW");
                    }
                }
                while(mis.RW<0){
                    wache.avalibleRW= (byte) (wache.avalibleRW+1);
                    mis.RW++;
                    if(debugLvl>=3){
                        System.out.println("REGAINED:RW");
                    }
                }
                alarme.remove(i);
            }
            i++;
        }
        driver.get("https://www.leitstellenspiel.de/missions/"+((Alarm) alarme.getFirst()).id);
        try {Thread.sleep(time*3);} catch (Exception e){};
    }
    public static void getNew() throws MissingMission {
        if(debugLvl>=4){
            System.out.println("START: GETNEW");
        }
        driver.get("https://www.leitstellenspiel.de/missions/"+((Alarm) alarme.getLast()).id);
        try {Thread.sleep(time*3);} catch (Exception e){};
        WebElement next = driver.findElement(By.cssSelector("#mission_next_mission_btn"));
        next.click();
        try {Thread.sleep(time*3);} catch (Exception e){};
        Alarm var = new Alarm(driver,wache,time);
        if(!var.id.equals(((Alarm) alarme.getLast()).id)){
            alarme.add(var);
            boolean test = true;
            try{var.alarmieren();}catch (MissingVehicle e){
                alarme.removeLast();
                if(debugLvl>=3){
                    System.out.println("Alarmierung Fehlgeschlagen");
                }
                test = false;
            }
            if(debugLvl>=2&&test){
                System.out.println("Neue Alarmierung: "+var.id+" "+var.missionId+" "+var.missionType.name);
            }
        }
        try {Thread.sleep(time);} catch (Exception e){};
    }
}