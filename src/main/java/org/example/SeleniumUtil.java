package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtil {

    public static int getMissionId(WebDriver driver) throws  IllegalStateException{
        WebElement var = driver.findElement(By.partialLinkText("Hilfe zu diesem Einsatz"));
        String link = var.getAttribute("href");
        String substring = link.substring(42,link.length()-1);
        substring = substring.split("m")[0];
        substring = substring.substring(0,substring.length()-1);
        return Integer.parseInt(substring);
    }
    public static int getMissionId(WebDriver driver,int alarmId,boolean ret) throws  IllegalStateException{
        String oldUrl = "";
        if(ret){
            oldUrl = driver.getCurrentUrl();
        }
        driver.get("https://www.leitstellenspiel.de/missions/"+alarmId);
        WebElement var = driver.findElement(By.partialLinkText("Hilfe zu diesem Einsatz"));
        String link = var.getAttribute("href");
        String substring = link.substring(42,link.length()-1);
        substring = substring.split("m")[0];
        substring = substring.substring(0,substring.length()-1);
        if(ret){
            driver.get(oldUrl);
        }
        return Integer.parseInt(substring);
    }
    public static long getAlarmId(WebDriver driver){
        String url = driver.getCurrentUrl();
        if(url.length()<42){
            throw new IllegalStateException();
        }
        String subUrl = url.substring(0,41);
        if(subUrl.equals("https://www.leitstellenspiel.de/missions/")){
            String subString = url.substring(41,url.length());
            return Long.parseLong(subString.split("#")[0]);
        }else{
            throw new IllegalStateException();
        }
    }
    public static void fahrzeugAlarmieren(WebDriver driver,String fahrzeug){
        driver.findElement(By.partialLinkText(fahrzeug)).click();
    }
    public static void alarmieren(WebDriver driver){
        driver.findElement(By.partialLinkText("Alarmieren")).click();
    }
    public static String getUrlFromAlarmId(long alarmId){
        return "https://www.leitstellenspiel.de/missions/"+alarmId;
    }
}
