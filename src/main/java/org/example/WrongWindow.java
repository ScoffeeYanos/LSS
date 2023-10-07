package org.example;

public class WrongWindow extends CustomError{
    public WrongWindow(int ID, String input){
        super(ID,input);
    }
    public WrongWindow(String input){
        super(-1,input);
    }
}
