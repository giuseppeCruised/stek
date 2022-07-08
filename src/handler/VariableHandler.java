package handler;

import domain.Item;

import java.util.HashMap;
import java.util.Stack;

public class VariableHandler {
    private Stack<HashMap<String, Item>> variableStack;

    public VariableHandler(){
        variableStack = new Stack<>();
    }

    public Item getVariable(String identifier){
        if(variableStack.empty()){
            System.out.println("Error: Variable "+identifier+" was not found");
            return null;
        }else{
            if(variableStack.peek().containsKey(identifier)){
                return variableStack.peek().get(identifier);
            }else{
                System.out.println("Error: variable "+identifier+"was not found");
                return null;
            }
        }
    }

    public void returnVariables(){
        variableStack.pop();
    }

    public boolean activeVariable(String identifier){
        if(variableStack.empty()){
            return false;
        }
        return variableStack.peek().containsKey(identifier);
    }

    public void addVariables(HashMap<String,Item> variables){
        variableStack.push(variables);
    }
}
