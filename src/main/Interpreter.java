package main;

import domain.*;
import handler.ErrorHandler;
import handler.VariableHandler;
import utils.Utility;

import java.util.HashMap;
import java.util.Stack;

public class Interpreter {
    private Stack<Item> itemStack;
    private Stack<Instruction> instructionStack;
    private HashMap<String, Method> Methods;
    VariableHandler variableHandler;

    public void interpretProgram(String program, Item[] args) {
        this.itemStack = new Stack<>();
        this.instructionStack = new Stack<>();
        this.Methods = new HashMap<>();
        this.variableHandler = new VariableHandler();

        String[] identityVariables = new String[1];
        identityVariables[0] = "b";
        Instruction[] identityInstructions = new Instruction[1];
        identityInstructions[0] = new Instruction("b",2);
        Method identity = new Method(identityInstructions,identityVariables);
        this.Methods.put("identity",identity);

        Instruction[] newMethod = new Instruction[2];
        newMethod[0] = new Instruction("a", 2);
        newMethod[1] = new Instruction("print", 2);
        String[] tmpVariables = new String[1];
        tmpVariables[0] = "a";
        Method method = new Method(newMethod, tmpVariables);

        this.Methods.put("printTwice", method);

        Instruction identityInstruction = new Instruction("identity",3);
        Instruction printTwice = new Instruction("$printTwice", 1);
        Instruction push3 = new Instruction("3", 1);

        Instruction push4 = new Instruction("4", 1);
        Instruction push5 = new Instruction("4", 1);
        instructionStack.push(identityInstruction);
        instructionStack.push(printTwice);
        instructionStack.push(push3);
        instructionStack.push(push4);
        instructionStack.push(push5);

        for (Item arg : args) {
            itemStack.push(arg);
        }

        this.runProgram();
    }

    public void runProgram() {
        while (!instructionStack.empty()) {
            Instruction currentInstruction = instructionStack.pop();
            Utility.log(currentInstruction);
            if (!ErrorHandler.canBeExecuted(currentInstruction, itemStack)) {
                break;
            } else if (Utility.isInteger(currentInstruction.getName())) {
                IntegerItem newItem = new IntegerItem(Integer.parseInt(currentInstruction.getName()));
                itemStack.push(newItem);
            } else if (currentInstruction.getName().equals("print")) {
                System.out.println("" + itemStack.pop().getValue());
            } else if (currentInstruction.getName().equals("+")) {
                IntegerItem firstItem = (IntegerItem) itemStack.pop();
                IntegerItem secondItem = (IntegerItem) itemStack.pop();
                IntegerItem addedItem = new IntegerItem(
                        (Integer) firstItem.getValue() + (Integer) secondItem.getValue());
                itemStack.push(addedItem);
            } else if (variableHandler.activeVariable(currentInstruction.getName())) {
                Item variable = variableHandler.getVariable(currentInstruction.getName());
                if (variable.getType() == Type.METHOD) {
                    instructionStack.push(new Instruction((String) variable.getValue(), currentInstruction.getLine()));
                } else {
                    itemStack.push(variableHandler.getVariable(currentInstruction.getName()));
                }
            } else if (currentInstruction.getName().equals("return")) {
                variableHandler.returnVariables();
            } else if (currentInstruction.getName().charAt(0) == '$') {
                String methodName = currentInstruction.getName().substring(1);
                if (Methods.containsKey(methodName)) {
                    itemStack.push(new MethodItem(methodName));
                } else if(variableHandler.activeVariable(methodName)) {
                    itemStack.push(variableHandler.getVariable(methodName));
                }
            } else {
                if (Methods.containsKey(currentInstruction.getName())) {
                    instructionStack.add(new Instruction("return", currentInstruction.getLine()));

                    Method method = Methods.get(currentInstruction.getName());
                    HashMap<String, Item> variables = new HashMap<>();
                    for (String identifier : method.getVariables()) {
                        variables.put(identifier, itemStack.pop());
                    }
                    variableHandler.addVariables(variables);
                    Instruction[] newInstructions = method.getInstructions();
                    for (Instruction instruction : Utility.reverseInstructions(newInstructions)) {
                        instructionStack.push(instruction);
                    }

                } else {
                    System.out.println("Error: Unknown Method");
                }
            }
        }
    }
}
