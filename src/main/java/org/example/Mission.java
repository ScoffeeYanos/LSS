package org.example;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public class Mission {
    Wache wache;
    int missionId;
    long alarmId;
    String name;
    WebDriver driver;
    static Object[][] fahrzeuge;
    static Object[][][] missionen;
    static int missionTotal;
    Object[][] mission;
    static boolean[] confimed;
    public Mission(Wache wache,long alarmId,int missionId,WebDriver driver) throws UnknownMission {
        this.wache = wache;
        this.alarmId = alarmId;
        this.missionId = missionId;
        this.driver = driver;
        if(confimed[missionId]==false){
            throw new UnknownMission("mission confirmer");
        }
        mission = Arrays.stream(missionen[missionId]).map(Object[]::clone).toArray(Object[][]::new);
    }
    public static void main(String[] args) throws UnknownMission {
        Fahrzeuge.init();
        init();
    }
    public static void init() throws UnknownMission {
        fahrzeuge = Fahrzeuge.getFahrzeuge();
        missionTotal = Integer.parseInt(ConfigReader.getConfig("MissionTotal"));
        missionen = new Object[missionTotal][0][0];
        confimed = new boolean[missionTotal];
        for(int i = 0;i<missionen.length;i++){
            missionen[i]=Fahrzeuge.getFahrzeuge();
        }
        for(int i = 0;i<missionen.length;i++){
            String var = ConfigReader.getConfig("Mission"+i);
            if(var == null){
                var = null;
                continue;
            }
            confimed[i] = true;
            String[] vars = var.split(",");
            for(int j = 0;j<vars.length;j++){
                String[] varsi = vars[j].split(":");
                for(int k = 0;k<missionen[0].length;k++){
                    if(missionen[i][k][2].equals(varsi[0])){
                        missionen[i][k][0]=Integer.parseInt(varsi[1]);
                        break;
                    }
                }
            }
        }
    }
    public boolean alarmieren() throws UnknownVehicle {
        wache.print();
        for(int i = 0;i< fahrzeuge.length;i++){
            if(wache.getAvalibleVehicleAmount(i)<((int)mission[i][0]-(int)mission[i][1])){
                return false;
            }
        }
        for(int i = 0;i< fahrzeuge.length;i++){
            while((int)mission[i][0]>(int)mission[i][1]){
                wache.decreaseAvalibleVehicle(i);
                mission[i][1] = (int)mission[i][1]+1;
                SeleniumUtil.fahrzeugAlarmieren(driver, (String) mission[i][2]);
            }
        }
        SeleniumUtil.alarmieren(driver);
        wache.print();
        return true;
    }
    public boolean free() throws UnknownVehicle {
        wache.print();
        for(int i = 0; i< fahrzeuge.length;i++){
            while((int)mission[i][1]!=0){
                wache.increaseAvalibleVehicle(i);
                mission[i][1] = (int)mission[i][1]-1;
            }
        }
        wache.print();
        return true;
    }
    public void recalc(int newMissionId){
        for(int i = 0; i<fahrzeuge.length; i++){
            mission[i][0] = missionen[newMissionId][i][0];
        }
    }
}
