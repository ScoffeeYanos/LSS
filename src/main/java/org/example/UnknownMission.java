package org.example;

public class UnknownMission extends CustomError{
    public UnknownMission(int ID, String input){
        super(ID,input);
    }
    public UnknownMission(String input){
        super(-1,input);
    }
}
