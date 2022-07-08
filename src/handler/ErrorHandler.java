package handler;

import domain.Instruction;
import domain.Item;
import domain.Type;

import java.util.Stack;

public class ErrorHandler {
    public static boolean canBeExecuted(Instruction instruction, Stack<Item> itemStack){
        if(instruction.getName().equals("+")){
            if (!itemStack.empty()) {
                if (itemStack.peek().getType() == Type.INTEGER) {
                    if (!itemStack.empty()) {
                        if (itemStack.peek().getType() == Type.INTEGER) {
                            return true;
                        } else {
                            System.out.println("Error: Can't add tew non Numbers together");
                        }
                    } else {
                        System.out.println("Error: Stack empty");
                    }
                } else {
                    System.out.println("Error: Can't add tew non Numbers together");
                }
            } else {
                System.out.println("Error: Stack empty");
            }
            return false;
        }else {
            return true;
        }

    }
}
