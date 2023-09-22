package org.example;

public class Statistics extends Thread{
    static int time;
    boolean run = true;
    boolean dead = false;
    byte debugLvl;
    Wache wache;
    double unit;
    //FW
    double LF = 0;
    double TLF = 0;
    double ELW = 0;
    double DLK = 0;
    double RW = 0;
    double SW = 0;
    double GW = 0;
    double MTW = 0;
    double WLF = 0;
    double FwK = 0;
    public Statistics(Wache wache,byte debugLvl,int time){
        this.wache = wache;
        this.debugLvl = debugLvl;
        this.time = time;
    }
    public void run(){
        if(debugLvl>=2){
            System.out.println("Statistics running");
        }
        while(run){
            record();
            try {Thread.sleep(time*500);} catch (Exception e){};
        }
        dead = true;
    }
    public void record(){
        unit++;
        LF = LF+wache.avalibleLF;
        TLF = TLF+wache.avalibleTLF;
        ELW = ELW+wache.avalibleELW;
        DLK = DLK+wache.avalibleDLK;
        RW = RW+wache.avalibleRW;
        SW = SW+wache.avalibleSW;
        GW = GW+wache.avalibleGW;
        MTW = MTW+wache.avalibleMTW;
        WLF = WLF+wache.avalibleWLF;
        FwK = FwK+wache.avalibleFwK;
    }
    public void print(){
        //TODO: limit for div 0
        if(wache.LF>0){
            double LFp = 100-(LF/(unit*wache.LF))*100;
            System.out.println("LF: "+LFp+"%");
        }
        if(wache.TLF>0){
            double TLFp = 100-(TLF/(unit*wache.TLF))*100;
            System.out.println("TLF: "+TLFp+"%");
        }
        if(wache.ELW>0){
            double ELWp = 100-(ELW/(unit*wache.ELW))*100;
            System.out.println("ELW: "+ELWp+"%");
        }
        if(wache.DLK>0){
            double DLKp = 100-(DLK/(unit*wache.DLK))*100;
            System.out.println("DLK: "+DLKp+"%");
        }
        if(wache.RW>0){
            double RWp = 100-(RW/(unit*wache.RW))*100;
            System.out.println("RW: "+RWp+"%");
        }
        if(wache.SW>0){
            double SWp = 100-(SW/(unit*wache.SW))*100;
            System.out.println("SW: "+SWp+"%");
        }
        if(wache.GW>0){
            double GWp = 100-(GW/(unit*wache.GW))*100;
            System.out.println("GW: "+GWp+"%");
        }
        if(wache.MTW>0){
            double MTWp = 100-(MTW/(unit*wache.MTW))*100;
            System.out.println("MTW: "+MTWp+"%");
        }
        if(wache.WLF>0){
            double WLFp = 100-(WLF/(unit*wache.WLF))*100;
            System.out.println("WLF: "+WLFp+"%");
        }
        if(wache.FwK>0){
            double FwKp = 100-(FwK/(unit*wache.FwK))*100;
            System.out.println("FwK: "+FwKp+"%");
        }
    }
}
