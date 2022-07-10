package main;

import domain.BooleanItem;
import domain.IntegerItem;
import domain.Item;
import domain.Method;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try {
            Interpreter interpreter = new Interpreter();
            Item[] arguments = new Item[1];
            arguments[0] = new IntegerItem(10);
            HashMap<String, Method> methods =
                    Lexer.lexMethods("C:/Users/AnwenderIN/Desktop/Projects/stek/src/programs/testProgramm.txt");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("STARTING PROGRAMM");
            System.out.println("-------------------------------------------------------------------------------");
            interpreter.interpretProgram(methods, arguments);

        } catch (IOException e) {
            System.out.println("DUHS");
        }


    }
}