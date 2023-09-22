package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public class Alarm {
    static int time;
    WebDriver driver;
    String id;
    String missionId;
    Mission missionType;
    Wache wache;
    public Alarm(){
        id = "0";
    }
    public Alarm(WebDriver driver,Wache wache,int time) throws MissingMission {
        this.driver = driver;
        this.wache = wache;
        this.time = time;
        id = getId();
        try {Thread.sleep(time);} catch (Exception e){};
        missionId = getMissionId();
        try {Thread.sleep(time);} catch (Exception e){};
        missionType = getMissionType();
        try {Thread.sleep(time);} catch (Exception e){};
    }
    public String getId() throws IllegalStateException{
        String url = driver.getCurrentUrl();
        if(url.length()<42){
            throw new IllegalStateException();
        }
        String subUrl = url.substring(0,41);
        if(subUrl.equals("https://www.leitstellenspiel.de/missions/")){
            String subString = url.substring(41,url.length());
            return subString.split("#")[0];
        }else{
            throw new IllegalStateException();
        }
    }
    public String getMissionId() throws  IllegalStateException{
        String id = null;
        try{id = getId();}catch(IllegalStateException e){}
        if(this.id.equals(id)){
            try {Thread.sleep(time);} catch (Exception e){};
            WebElement var = driver.findElement(By.partialLinkText("Hilfe zu diesem Einsatz"));
            String link = var.getAttribute("href");
            String substring = link.substring(42,link.length()-1);
            substring = substring.split("m")[0];
            substring = substring.substring(0,substring.length()-1);
            return substring;
        }else{
            throw new IllegalStateException();
        }
    }
    public Mission getMissionType() throws IllegalStateException, IllegalArgumentException, MissingMission {
        if(missionId!=null){
            Class cls;
            String ClassName = "org.example.missionType.Mission"+missionId;
            try{cls = Class.forName(ClassName);}catch (Exception e){throw new MissingMission(missionId);}
            if(cls!= null){
                Mission var = null;
                try{var = (Mission) cls.getDeclaredConstructor().newInstance();}catch (Exception e){ throw new IllegalStateException();}
                return var;
            }else {
                throw new IllegalStateException();
            }
        }else{
            throw new IllegalStateException();
        }
    }

    public static Mission getMissionType(String missionId) throws IllegalStateException, IllegalArgumentException, MissingMission {
        if(missionId!=null){
            Class cls;
            String ClassName = "org.example.missionType.Mission"+missionId;
            try{cls = Class.forName(ClassName);}catch (Exception e){throw new MissingMission(missionId);}
            if(cls!= null){
                Mission var = null;
                try{var = (Mission) cls.getDeclaredConstructor().newInstance();}catch (Exception e){ throw new IllegalStateException();}
                return var;
            }else {
                throw new IllegalStateException();
            }
        }else{
            throw new IllegalStateException();
        }
    }
    public void alarmieren() throws MissingVehicle {
        WebElement var;
        //FW
        if(missionType.LF> wache.avalibleLF&&wache.LF!=0){
            throw new MissingVehicle("LF");
        }
        if(missionType.TLF> wache.avalibleTLF&&wache.TLF!=0){
            throw new MissingVehicle("TLF");
        }
        if(missionType.ELW> wache.avalibleELW&&wache.ELW!=0){
            throw new MissingVehicle("ELW");
        }
        if(missionType.DLK> wache.avalibleDLK&&wache.DLK!=0){
            throw new MissingVehicle("DLK");
        }
        if(missionType.RW> wache.avalibleRW&&wache.RW!=0){
            throw new MissingVehicle("RW");
        }
        if(missionType.SW> wache.avalibleSW&&wache.SW!=0){
            throw new MissingVehicle("SW");
        }
        if(missionType.GW> wache.avalibleGW&&wache.GW!=0){
            throw new MissingVehicle("GW");
        }
        if(missionType.MTW> wache.avalibleMTW&&wache.MTW!=0){
            throw new MissingVehicle("MTW");
        }
        if(missionType.WLF> wache.avalibleWLF&&wache.WLF!=0){
            throw new MissingVehicle("WLF");
        }
        if(missionType.FwK> wache.avalibleFwK&&wache.FwK!=0){
            throw new MissingVehicle("FwK");
        }
        //FW
        while(missionType.LF>0&&wache.LF!=0){
            missionType.LF--;
            wache.avalibleLF--;
            var = driver.findElement(By.partialLinkText("LF"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.TLF>0&&wache.TLF!=0){
            missionType.TLF--;
            wache.avalibleTLF--;
            var = driver.findElement(By.partialLinkText("TLF"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.ELW>0&&wache.ELW!=0){
            missionType.ELW--;
            wache.avalibleELW--;
            var = driver.findElement(By.partialLinkText("ELW"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.DLK>0&&wache.DLK!=0){
            missionType.DLK--;
            wache.avalibleDLK--;
            var = driver.findElement(By.partialLinkText("DLK"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.RW>0&&wache.RW!=0){
            missionType.RW--;
            wache.avalibleRW--;
            var = driver.findElement(By.partialLinkText("RW"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.SW>0&&wache.SW!=0){
            missionType.SW--;
            wache.avalibleSW--;
            var = driver.findElement(By.partialLinkText("SW"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.GW>0&&wache.GW!=0){
            missionType.GW--;
            wache.avalibleGW--;
            var = driver.findElement(By.partialLinkText("GW"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.MTW>0&&wache.MTW!=0){
            missionType.MTW--;
            wache.avalibleMTW--;
            var = driver.findElement(By.partialLinkText("MTW"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.WLF>0&&wache.WLF!=0){
            missionType.WLF--;
            wache.avalibleWLF--;
            var = driver.findElement(By.partialLinkText("WLF"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        while(missionType.FwK>0&&wache.FwK!=0){
            missionType.FwK--;
            wache.avalibleFwK--;
            var = driver.findElement(By.partialLinkText("FwK"));
            var.click();
            try {Thread.sleep(time);} catch (InterruptedException e) {}
        }
        var = driver.findElement(By.partialLinkText("Alarmieren"));
        try {Thread.sleep(time/2);} catch (InterruptedException e) {}
        var.click();
        try {Thread.sleep(time);} catch (InterruptedException e) {}
    }
}
