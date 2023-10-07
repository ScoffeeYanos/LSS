package org.example;
public class Wache {
    byte debugLevel;
    private Object[][] fahrzeuge;
    public Wache(byte debugLevel) throws UnknownMission {
        this.debugLevel = debugLevel;
        fahrzeuge = Fahrzeuge.getFahrzeuge();
    }
    public void resetAvalible(){
        for(int i = 0;i<fahrzeuge.length;i++){
            fahrzeuge[i][1]=fahrzeuge[i][0];
        }
    }
    public void increaseVehicle(String vehicle) throws UnknownVehicle {
        setVehicle(vehicle,getVehicleAmount(vehicle)+1);
    }
    public void decreaseVehicle(String vehicle) throws UnknownVehicle {
        setVehicle(vehicle,getVehicleAmount(vehicle)-1);
    }
    public void increaseVehicle(int id) throws UnknownVehicle {
        setVehicle(id,getVehicleAmount(id)+1);
    }
    public void decreaseVehicle(int id) throws UnknownVehicle {
        setVehicle(id,getVehicleAmount(id)-1);
    }
    public int getVehicleID(String vehicle) throws UnknownVehicle {
        for(int i = 0;i<fahrzeuge.length;i++){
            if(fahrzeuge[i][2]!=null&&fahrzeuge[i][2].equals(vehicle)){
                return i;
            }
        }
        throw new UnknownVehicle(vehicle);
    }
    public int getVehicleAmount(String vehicle) throws UnknownVehicle {
        for(int i = 0;i<fahrzeuge.length;i++){
            if(fahrzeuge[i][2]!=null&&fahrzeuge[i][2].equals(vehicle)){
                return (int) fahrzeuge[i][0];
            }
        }
        throw new UnknownVehicle(vehicle);
    }
    public int getVehicleAmount(int ID){
        return (int) fahrzeuge[ID][0];
    }
    public void setVehicle(String vehicle,int value){
        for(int i = 0;i<fahrzeuge.length;i++){
            if(fahrzeuge[i][2]!=null&&fahrzeuge[i][2].equals(vehicle)){
                fahrzeuge[i][0]=value;
                return;
            }
        }
    }
    public void setVehicle(int ID,int value){
        fahrzeuge[ID][0]=value;
    }
    public void increaseAvalibleVehicle(String vehicle) throws UnknownVehicle {
        setAvalibleVehicle(vehicle,getVehicleAmount(vehicle)+1);
    }
    public void decreaseAvalibleVehicle(String vehicle) throws UnknownVehicle {
        setAvalibleVehicle(vehicle,getVehicleAmount(vehicle)-1);
    }
    public void increaseAvalibleVehicle(int id) throws UnknownVehicle {
        setAvalibleVehicle(id,getAvalibleVehicleAmount(id)+1);
    }
    public void decreaseAvalibleVehicle(int id) throws UnknownVehicle {
        setAvalibleVehicle(id,getAvalibleVehicleAmount(id)-1);
    }
    public int getAvalibleVehicleAmount(String vehicle) throws UnknownVehicle {
        for(int i = 0;i<fahrzeuge.length;i++){
            if(fahrzeuge[i][2]!=null&&fahrzeuge[i][2].equals(vehicle)){
                return (int) fahrzeuge[i][1];
            }
        }
        throw new UnknownVehicle(vehicle);
    }
    public int getAvalibleVehicleAmount(int ID){
        return (int) fahrzeuge[ID][1];
    }
    public void setAvalibleVehicle(String vehicle,int value){
        for(int i = 0;i<fahrzeuge.length;i++){
            if(fahrzeuge[i][2]!=null&&fahrzeuge[i][2].equals(vehicle)){
                fahrzeuge[i][1]=value;
                return;
            }
        }
    }
    public void setAvalibleVehicle(int ID,int value){
        fahrzeuge[ID][1]=value;
    }
    public void refresh() throws UnknownMission {
        for(int i = 0;i<fahrzeuge.length;i++){
            String var = ConfigReader.getConfig((String) fahrzeuge[i][2]);
            if(var==null){
                continue;
            }
            System.out.println(var);
            fahrzeuge[i][0] = Integer.parseInt(var);
        }
    }
    public void print(){
        for(int i = 0;i< fahrzeuge.length;i++){
            System.out.print(fahrzeuge[i][2]+": ("+fahrzeuge[i][1]+"|"+fahrzeuge[i][0]+")  ");
        }
        System.out.println("");
    }
}
