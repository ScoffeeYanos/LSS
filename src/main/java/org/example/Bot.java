package org.example;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Bot extends Thread{
    byte debugLevel;
    boolean pause;
    Config config;
    Wache wache;
    WebDriver driver;
    String mainWindowHandle;
    ArrayList<Mission> missions;
    PageState pageState;
    public Bot(byte debugLevel,boolean pause,Config config,Wache wache,String login,String pw) throws UnknownMission {
        this.debugLevel = debugLevel;
        this.pause = pause;
        this.config = config;
        this.wache = wache;
        init(login,pw);
    }
    public void init(String login,String pw) throws UnknownMission {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.leitstellenspiel.de/users/sign_in");
        pageState = PageState.login;
        mainWindowHandle = driver.getWindowHandle();
        login(login,pw);
        openAlarm();
        missions = new ArrayList<Mission>();
        wache.refresh();
        wache.resetAvalible();
    }
    public void run(){
        while(true){
            if(pause){
                Util.sleep(3);
                continue;
            }
            if(!missions.isEmpty()){
                try {
                    System.out.println("cycle");
                    cycle();
                } catch (Exception | UnknownVehicle e) {
                    System.out.println("THROW MAIN RUN:GetNext");
                }
                if(getNext()){
                    try {
                        System.out.println("newAlert:getNext");
                        newAlert();
                    } catch (Exception | UnknownMission | UnknownVehicle e) {
                        System.out.println("THROW MAIN RUN:GetNext");
                    }
                }
            }else{
                try {
                    System.out.println("newAlert");
                    newAlert();
                } catch (Exception | UnknownMission | UnknownVehicle e) {
                    System.out.println("THROW MAIN RUN:newAlert");
                }
            }
            System.out.println("Sleep:3s");
            Util.sleepSec(3);
        }
    }
    public void cycle() throws UnknownVehicle {
        for(int i = 0;i<missions.size();i++){
            driver.get(SeleniumUtil.getUrlFromAlarmId(missions.get(i).alarmId));
            Util.sleep(500);
            try{ //clear finished element
                driver.findElement(By.partialLinkText("Alarmieren"));
            } catch ( Exception e){
                missions.get(i).free();
                missions.remove(i);
                continue;
            }
            if(SeleniumUtil.getMissionId(driver)!=missions.get(i).missionId){// check for realarm
                System.out.println("ReAlarm");
                missions.get(i).recalc(SeleniumUtil.getMissionId(driver));
                if(missions.get(i).alarmieren()){
                    missions.get(i).missionId = SeleniumUtil.getMissionId(driver);
                }
            }
        }
    }
    public boolean getNext(){
        if(missions.size()==0){
            driver.get("leitstellenspiel.de");
            Util.sleep(200);
            openAlarm();
            return true;
        }
        try{
            driver.get(SeleniumUtil.getUrlFromAlarmId(missions.get(missions.size()-1).alarmId));
            Util.sleepSec(1);
            driver.findElement(By.cssSelector("#mission_next_mission_btn")).click();
            Util.sleepSec(1);
            if(SeleniumUtil.getAlarmId(driver)==missions.get(missions.size()-1).alarmId){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public void newAlert() throws UnknownMission, UnknownVehicle {
        Mission var = new Mission(wache,SeleniumUtil.getAlarmId(driver),SeleniumUtil.getMissionId(driver),driver);
        System.out.println("Neuw Alarmierung: MissionID:"+var.missionId+" AlarmID:"+var.alarmId);
        if(var.alarmieren()){
            missions.add(var);
        }else{
        }
    }
    public void login(String login,String pw){
        if(login.equals("")&&pw.equals("")){
            while(true){
                try{
                    WebElement var = driver.findElement(By.cssSelector("#new_user > input"));
                }
                catch (Exception e){
                    break;
                }
                Util.sleepSec(1);
            }
        }else{
            WebElement var;
            var = driver.findElement(By.cssSelector("#user_email"));
            var.sendKeys(login);
            var = driver.findElement(By.cssSelector("#user_password"));
            var.sendKeys(pw);
            var = driver.findElement(By.cssSelector("#new_user > input"));
            var.click();
        }
        pageState = PageState.map;
    }
    public void openAlarm(){
        while(driver.findElements(By.partialLinkText("Alarm")).size()==0){
            Util.sleepSec(1);
        }
        WebElement var = driver.findElement(By.partialLinkText("Alarm"));
        String link = var.getAttribute("href");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(link);
        pageState = PageState.alarm;
    }
}
