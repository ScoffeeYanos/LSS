package org.example;

public class CustomError extends Throwable{
    int ID;
    String input;
    public CustomError(int ID,String input){
        this.ID = ID;
        this.input = input;
        System.out.println(input);
    }
}
