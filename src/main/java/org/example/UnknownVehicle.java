package org.example;

public class UnknownVehicle extends CustomError{
    public UnknownVehicle(int ID, String input){
        super(ID,input);
    }
    public UnknownVehicle(String input){
        super(-1,input);
    }
}
