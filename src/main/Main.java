package main;

import domain.BooleanItem;
import domain.IntegerItem;
import domain.Item;
import domain.Method;
import reWritten.domain.Instruction;
import reWritten.domain.MethodInstruction;
import reWritten.parsemarse.InstructionParser;
import reWritten.parsemarse.SafeParsedElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//        try {
//            Interpreter interpreter = new Interpreter();
//            Item[] arguments = new Item[0];
////            arguments[0] = new IntegerItem(10);
//            HashMap<String, Method> methods =
//                    Lexer.lexMethods("C:/Users/AnwenderIN/Desktop/Projects/stek/src/programs/testProgramm.txt");
//            System.out.println("-------------------------------------------------------------------------------");
//            System.out.println("STARTING PROGRAMM");
//            System.out.println("-------------------------------------------------------------------------------");
//            interpreter.interpretProgram(methods, arguments);
//
//        } catch (IOException e) {
//            System.out.println("DUHS");
//        }
        InstructionParser iP = new InstructionParser();
        SafeParsedElement<Instruction> parsedElement = iP.runParser("12", 1, new String[0],
                new String[0]);
    }
}
