package org.example;

public class Config {
    byte debugLevel;
    int timeUnit;
    boolean pause;
    Bot bot;
    GUI gui;
    Wache[] wachen;
    Statistics[] statistics;
    public Config(String login,String pw) throws UnknownMission {
        preInit();
        init(login,pw);
    }
    public void preInit() throws UnknownMission {
        debugLevel = 0;
        timeUnit = 200;
        pause = false;
        Fahrzeuge.init();
        Mission.init();
    }
    public void init(String login,String pw) throws UnknownMission {
        wachen = new Wache[]{new Wache(debugLevel)};
        bot = new Bot(debugLevel,pause,this,wachen[0],login,pw);
        gui = new GUI(debugLevel,bot,this,pause);
        statistics = new Statistics[]{new Statistics(debugLevel,wachen[0])};
        Util.sleepSec(3);
        bot.start();
    }
    public void setDebuglevel(byte newDebugLevel){
        debugLevel = newDebugLevel;
        bot.debugLevel = newDebugLevel;
        gui.debugLevel = newDebugLevel;
        for(int i = 0;i<wachen.length;i++){
            wachen[i].debugLevel = debugLevel;
        }
        for(int i = 0;i<statistics.length;i++){
            statistics[i].debugLevel = debugLevel;
        }
    }
    public void switchPause(){
        boolean newPause = !pause;
        pause = newPause;
        bot.pause = newPause;
        gui.pause = newPause;
    }
    public void reloadMissions() throws UnknownMission {
        Mission.init();
    }
}
