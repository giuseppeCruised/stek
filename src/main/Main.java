package main;

import domain.Item;

public class Main {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        Item[] arguments = new Item[0];
        interpreter.interpretProgram("",arguments);


    }
}
