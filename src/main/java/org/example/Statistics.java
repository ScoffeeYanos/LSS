package org.example;

public class Statistics extends Thread{
    int unit;
    byte debugLevel;
    Object[][] fahrzeuge;
    Wache wache;
    public Statistics(byte debugLevel,Wache wache){
        this.debugLevel = debugLevel;
        this.fahrzeuge = Fahrzeuge.getFahrzeuge();
        this.wache = wache;
        unit = 0;
    }
    public void run(){
        while(true){
            record();
            Util.sleepSec(1);
        }
    }
    public void record(){
        unit++;
        for(int i = 0;i<fahrzeuge.length;i++){
            fahrzeuge[i][1] = wache.getAvalibleVehicleAmount(i);
            fahrzeuge[i][0] = wache.getVehicleAmount(i);
        }
    }
    public double[] print(){
        double[] ret = new double[fahrzeuge.length];
        for(int i = 0;i<fahrzeuge.length;i++){
            if((int)fahrzeuge[i][0]!=0){
                ret[i] = 100d-((double)fahrzeuge[i][1]/((double)fahrzeuge[i][0]))*100d;
            }else{
                ret[i] = -1;
            }
        }
        return ret;
    }
}
