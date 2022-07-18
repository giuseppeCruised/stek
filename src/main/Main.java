package main;

import reWritten.parsemarse.MethodParser;

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
//        InstructionParser iP = new InstructionParser();
//        SafeParsedElement<Instruction> parsedElement = iP.runParser("12", 1, new String[0],
//                new String[0]);
        String[] test = new String[2];
        test[0] = "yeah: a b => ";
        test[1] = "";

        MethodParser.runParser(test, 0, null,"yeah");
    }
}
