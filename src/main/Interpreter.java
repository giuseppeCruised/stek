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

    public void interpretProgram(HashMap<String, Method> Methods, Item[] args) {
        this.itemStack = new Stack<>();
        this.instructionStack = new Stack<>();
        this.Methods = Methods;
        this.variableHandler = new VariableHandler();

        instructionStack.push(new Instruction("MAIN", 1));

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
            } else if (currentInstruction.getName().equals("if")) {
                MethodItem method = (MethodItem) itemStack.pop();
                BooleanItem predicate = (BooleanItem) itemStack.pop();
                if ((Boolean) predicate.getValue()) {
                    instructionStack.push(new Instruction((String) method.getValue(), currentInstruction.getLine()));
                }
            } else if (currentInstruction.getName().equals("==")) {
                this.handleEquals(currentInstruction.getLine());
            } else if (currentInstruction.getName().equals("TRUE")) {
                BooleanItem newItem = new BooleanItem(true);
                itemStack.push(newItem);
            } else if (currentInstruction.getName().equals("FALSE")) {
                BooleanItem newItem = new BooleanItem(false);
                itemStack.push(newItem);
            } else if (variableHandler.activeVariable(currentInstruction.getName())) {
                Item variable = variableHandler.getVariable(currentInstruction.getName());
                itemStack.push(variableHandler.getVariable(currentInstruction.getName()));
            } else if (currentInstruction.getName().equals("!")) {
                Item item = itemStack.pop();
                if (item.getType() == Type.METHOD) {
                    instructionStack.push(new Instruction((String) item.getValue(), currentInstruction.getLine()));
                } else {
                    itemStack.push(item);
                }
            } else if (currentInstruction.getName().equals("return")) {
                variableHandler.returnVariables();
            } else if (currentInstruction.getName().equals("not")) {
                this.handleNot(currentInstruction.getLine());
            } else if (currentInstruction.getName().equals("and")) {
                this.handleAnd(currentInstruction.getLine());
            } else if (currentInstruction.getName().equals("or")) {
                this.handleOr(currentInstruction.getLine());
            } else if (currentInstruction.getName().equals("dup")) {
                this.handleDup(currentInstruction.getLine());
            } else if (currentInstruction.getName().charAt(0) == '?') {
                String methodName = currentInstruction.getName().substring(1);
                if (Methods.containsKey(methodName)) {
                    itemStack.push(new MethodItem(methodName));
                } else if (variableHandler.activeVariable(methodName)) {
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

    private void handleDup(int currentLine){
        Item item = itemStack.pop();
        itemStack.push(item);
        itemStack.push(item);
    }

    private void handleAnd(int currentLine){
        BooleanItem item1 = (BooleanItem) itemStack.pop();
        BooleanItem item2 = (BooleanItem) itemStack.pop();
        itemStack.push(new BooleanItem((Boolean) item1.getValue() && (Boolean) item2.getValue()));
    }

    private void handleOr(int currentLine){
        BooleanItem item1 = (BooleanItem) itemStack.pop();
        BooleanItem item2 = (BooleanItem) itemStack.pop();
        itemStack.push(new BooleanItem((Boolean) item1.getValue() || (Boolean) item2.getValue()));
    }

    private void handleNot(int currentLine){
        BooleanItem item = (BooleanItem) itemStack.pop();
        if((Boolean) item.getValue()){
            itemStack.push(new BooleanItem(false));
        } else {
            itemStack.push(new BooleanItem(true));
        }

    }

    private void handleEquals(int currentLine) {
        Item item1 = itemStack.pop();
        Item item2 = itemStack.pop();
        if (item1.getType() == item2.getType()) {
            BooleanItem result = new BooleanItem(false);
            switch (item1.getType()) {
                case INTEGER:
                    result = new BooleanItem((Integer) item1.getValue() == (Integer) item2.getValue());
                    break;
                case BOOLEAN:
                    result = new BooleanItem((Boolean) item1.getValue() == (Boolean) item2.getValue());
                    break;
                case METHOD:
                    result = new BooleanItem((String) item1.getValue() == (String) item2.getValue());
                    break;
            }
            itemStack.push(result);
        } else {
            System.out.println("Error: Type mismatch == , at Line: " + currentLine);
        }
    }
}
